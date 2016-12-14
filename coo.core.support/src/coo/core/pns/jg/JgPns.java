package coo.core.pns.jg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.schedule.ScheduleResult;
import coo.base.util.CollectionUtils;
import coo.base.util.DateUtils;
import coo.core.pns.PnsNotify;
import coo.core.pns.PnsScheduleNotify;

/**
 * 极光消息推送。
 */
public class JgPns {
  private Logger log = LoggerFactory.getLogger(getClass());
  private JgPnsConfig config;
  private JPushClient jpushClient;
  private ClientConfig jpushClientConfig;

  /**
   * 构造方法。
   * 
   * @param config 极光推送配置
   */
  public JgPns(JgPnsConfig config) {
    this.config = config;
    jpushClientConfig = ClientConfig.getInstance();
    jpushClientConfig.setConnectionTimeout(config.getConnTimeout() * 1000);
    jpushClientConfig.setMaxRetryTimes(config.getRetryTimes());
    jpushClientConfig.setReadTimeout(config.getReadTimeout() * 1000);
    jpushClient =
        new JPushClient(config.getMasterSecret(), config.getAppKey(), null, jpushClientConfig);
  }

  /**
   * 发送通知。
   * 
   * @param notify 推送通知
   */
  public void sendPush(PnsNotify notify) {
    if (CollectionUtils.isEmpty(notify.getAliases())) {
      log.warn("极光推送别名列表为空，未推送消息：{}", notify.getContent());
      return;
    }
    try {
      PushPayload pushPayload = genPushPayLoad(notify);
      PushResult pushResult = jpushClient.sendPush(pushPayload);
      log.info("极光推送返回消息：{}", pushResult);
    } catch (APIConnectionException | APIRequestException e) {
      log.error("极光推送消息失败：{}。", e.getMessage());
    } catch (Exception e) {
      log.error("极光推送消息异常。", e);
    }
  }

  /**
   * 创建定时通知。
   * 
   * @param notify 定时推送通知
   * @return 返回定时推送任务ID。
   */
  public String createSingleSchedule(PnsScheduleNotify notify) {
    if (CollectionUtils.isEmpty(notify.getAliases())) {
      log.warn("极光推送别名列表为空，未推送消息：{}", notify.getContent());
      return null;
    }
    try {
      PushPayload pushPayload = genPushPayLoad(notify);
      ScheduleResult pushResult = jpushClient.createSingleSchedule(notify.getTaskName(),
          DateUtils.format(notify.getSendTime(), DateUtils.SECOND), pushPayload);
      log.info("极光创建定时推送返回消息：{}", pushResult);
      return pushResult.getSchedule_id();
    } catch (APIConnectionException | APIRequestException e) {
      log.error("极光创建定时推送消息失败：{}。", e.getMessage());
      return null;
    } catch (Exception e) {
      log.error("极光创建定时推送消息异常。", e);
      return null;
    }
  }

  /**
   * 删除定时通知。
   * 
   * @param scheduleId 定时通知ID
   */
  public void deleteSchedule(String scheduleId) {
    try {
      jpushClient.deleteSchedule(scheduleId);
      log.info("极光删除定时推送消息：{}", scheduleId);
    } catch (APIConnectionException | APIRequestException e) {
      log.error("极光删除定时推送消息失败：{}。", e.getMessage());
    } catch (Exception e) {
      log.error("极光删除定时推送消息异常。", e);
    }
  }

  /**
   * 生成推送对象。
   * 
   * @param notify 推送通知
   * @return 返回推送对象。
   */
  private PushPayload genPushPayLoad(PnsNotify notify) {
    AndroidNotification androidNotification = genAndroidNotification(notify);
    IosNotification iosNotification = genIosNotification(notify);
    Notification notification =
        Notification.newBuilder().addPlatformNotification(androidNotification)
            .addPlatformNotification(iosNotification).build();
    Options options = Options.newBuilder().setApnsProduction(config.getProduction())
        .setTimeToLive(config.getLiveTime()).build();
    return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setOptions(options)
        .setAudience(Audience.alias(notify.getAliases())).setNotification(notification).build();
  }

  /**
   * 生成Android通知。
   * 
   * @param notify 推送通知
   * @return 返回Android通知。
   */
  private AndroidNotification genAndroidNotification(PnsNotify notify) {
    AndroidNotification.Builder builder = AndroidNotification.newBuilder();
    builder.setAlert(notify.getContent()).setTitle(notify.getTitle()).addExtras(notify.getExtras());
    return builder.build();
  }

  /**
   * 生成IOS通知。
   * 
   * @param notify 推送通知
   * @return 返回IOS通知。
   */
  private IosNotification genIosNotification(PnsNotify notify) {
    IosNotification.Builder builder = IosNotification.newBuilder();
    builder.setAlert(notify.getContent()).setSound(notify.getSound()).addExtras(notify.getExtras());
    return builder.build();
  }
}
