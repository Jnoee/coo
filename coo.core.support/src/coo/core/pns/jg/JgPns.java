package coo.core.pns.jg;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

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
  /** 应用ID */
  @Value("${pns.appKey}")
  private String appKey;
  /** 密码 */
  @Value("${pns.masterSecret}")
  private String masterSecret;
  /** 是否生产环境 */
  @Value("${pns.production:false}")
  private Boolean production;
  /** 消息存活时间（秒） */
  @Value("${pns.live:300}")
  private Long liveTime;
  /** 连接超时时间（秒） */
  @Value("${pns.connTimeout:5}")
  private Integer connTimeout;
  /** 失败重发次数 */
  @Value("${pns.retryTimes:3}")
  private Integer retryTimes;
  /** 读取返回结果超时时间（秒） */
  @Value("${pns.readTimeout:30}")
  private Integer readTimeout;

  private JPushClient client;
  private ClientConfig config;

  /**
   * 初始化。
   */
  @PostConstruct
  protected void init() {
    config = ClientConfig.getInstance();
    config.setConnectionTimeout(connTimeout * 1000);
    config.setMaxRetryTimes(retryTimes);
    config.setReadTimeout(readTimeout * 1000);
    client = new JPushClient(masterSecret, appKey, null, config);
  }

  /**
   * 发送通知。
   * 
   * @param notify 推送通知
   */
  @Async
  public void sendPush(PnsNotify notify) {
    if (CollectionUtils.isEmpty(notify.getAliases())) {
      log.warn("极光推送别名列表为空，未推送消息：{}", notify.getContent());
      return;
    }
    try {
      PushPayload pushPayload = genPushPayLoad(notify);
      PushResult pushResult = client.sendPush(pushPayload);
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
  @Async
  public String createSingleSchedule(PnsScheduleNotify notify) {
    if (CollectionUtils.isEmpty(notify.getAliases())) {
      log.warn("极光推送别名列表为空，未推送消息：{}", notify.getContent());
      return null;
    }
    try {
      PushPayload pushPayload = genPushPayLoad(notify);
      ScheduleResult pushResult = client.createSingleSchedule(notify.getTaskName(),
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
  @Async
  public void deleteSchedule(String scheduleId) {
    try {
      client.deleteSchedule(scheduleId);
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
    Options options =
        Options.newBuilder().setApnsProduction(production).setTimeToLive(liveTime).build();
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
