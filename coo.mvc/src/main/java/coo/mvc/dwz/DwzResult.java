package coo.mvc.dwz;

import java.util.ArrayList;
import java.util.List;

/**
 * DWZ的Ajax响应内容Model。
 */
public class DwzResult {
  private String statusCode;
  private String message;
  private List<String> closeNavTab = new ArrayList<>();
  private List<String> closeDialog = new ArrayList<>();
  private List<String> reloadNavTab = new ArrayList<>();
  private List<String> reloadDialog = new ArrayList<>();
  private List<String> reloadDiv = new ArrayList<>();

  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getCloseNavTab() {
    return closeNavTab;
  }

  public void setCloseNavTab(List<String> closeNavTab) {
    this.closeNavTab = closeNavTab;
  }

  public List<String> getCloseDialog() {
    return closeDialog;
  }

  public void setCloseDialog(List<String> closeDialog) {
    this.closeDialog = closeDialog;
  }

  public List<String> getReloadNavTab() {
    return reloadNavTab;
  }

  public void setReloadNavTab(List<String> reloadNavTab) {
    this.reloadNavTab = reloadNavTab;
  }

  public List<String> getReloadDialog() {
    return reloadDialog;
  }

  public void setReloadDialog(List<String> reloadDialog) {
    this.reloadDialog = reloadDialog;
  }

  public List<String> getReloadDiv() {
    return reloadDiv;
  }

  public void setReloadDiv(List<String> reloadDiv) {
    this.reloadDiv = reloadDiv;
  }
}
