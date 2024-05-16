package model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class WorktimeBean implements Serializable {

	private int id;
	private String userId;
	private Date date;
	private Time workIn;
	private Time workOut;
	private Time breakTime;
	private Time overTime;

	public WorktimeBean() {
	}

	/**
	 * @param id id
	 * @param userId ユーザーID
	 * @param date 日付
	 * @param workIn 始業時間
	 * @param workOut 終業時間
	 * @param breakTime 休憩時間
	 * @param overTime 残業時間
	 */
	public WorktimeBean(int id, String userId, Date date, Time workIn, Time workOut, Time breakTime,
			Time overTime) {
		this.id = id;
		this.userId = userId;
		this.date = date;
		this.workIn = workIn;
		this.workOut = workOut;
		this.breakTime = breakTime;
		this.overTime = overTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getWorkIn() {
		return workIn;
	}

	public void setWorkIn(Time workIn) {
		this.workIn = workIn;
	}

	public Time getWorkOut() {
		return workOut;
	}

	public void setWorkOut(Time workOut) {
		this.workOut = workOut;
	}

	public Time getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(Time breakTime) {
		this.breakTime = breakTime;
	}

	public Time getOverTime() {
		return overTime;
	}

	public void setOverTime(Time overTime) {
		this.overTime = overTime;
	}

}
