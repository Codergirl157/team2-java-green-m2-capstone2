package com.techelevator;

import java.math.BigDecimal;
import java.util.Date;

public class Space {

  private Long spaceId;
  private String spaceName;
  private int maxOccupancy;
  private int openMonth;
  private int closeMonth;
  private boolean isAccessible;
  private BigDecimal dailyRate;


    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public int getOpenMonth() {
        return openMonth;
    }

    public void setOpenMonth(int openMonth) {
        this.openMonth = openMonth;
    }

    public int getCloseMonth() {
        return closeMonth;
    }

    public void setCloseMonth(int closeMonth) {
        this.closeMonth = closeMonth;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }
}
