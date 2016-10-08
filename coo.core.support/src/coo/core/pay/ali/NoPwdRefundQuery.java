package coo.core.pay.ali;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import coo.base.util.DateUtils;

/**
 * 无密退款请求。
 */
public class NoPwdRefundQuery extends AliPayQuery {
  @XStreamAlias("seller_user_id")
  private String sellerId;
  @XStreamAlias("refund_date")
  private String refundDate;
  @XStreamAlias("batch_no")
  private String batchNo;
  @XStreamAlias("batch_num")
  private String batchNum = "1";
  @XStreamAlias("detail_data")
  private String detailData;

  /**
   * 构造方法。
   * 
   * @param data 业务数据
   */
  public NoPwdRefundQuery(NoPwdRefundData data) {
    service = "refund_fastpay_by_platform_nopwd";
    refundDate = DateUtils.format(new Date(), DateUtils.SECOND);
    batchNo = data.getBatchNo();
    detailData = data.getTradeNo() + "^" + data.getAmount() + "^" + data.getRemark();
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  public String getRefundDate() {
    return refundDate;
  }

  public void setRefundDate(String refundDate) {
    this.refundDate = refundDate;
  }

  public String getBatchNo() {
    return batchNo;
  }

  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo;
  }

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  public String getDetailData() {
    return detailData;
  }

  public void setDetailData(String detailData) {
    this.detailData = detailData;
  }
}
