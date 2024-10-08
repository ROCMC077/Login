package tw.lai.model;

import org.springframework.stereotype.Component;

@Component
public class Member {

	private int member_id;

	private String member_username;

	private String member_gender;

	private long date;

	private int member_status;

	public Member() {
		super();
	}

	public Member(int member_id, String member_username, String member_gender, long date, int member_status) {
		super();
		this.member_id = member_id;
		this.member_username = member_username;
		this.member_gender = member_gender;
		this.date = date;
		this.member_status = member_status;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getMember_username() {
		return member_username;
	}

	public void setMember_username(String member_username) {
		this.member_username = member_username;
	}

	public String getMember_gender() {
		return member_gender;
	}

	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getMember_status() {
		return member_status;
	}

	public void setMember_status(int member_status) {
		this.member_status = member_status;
	}

	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", member_username=" + member_username + ", member_gender="
				+ member_gender + ", date=" + date + ", member_status=" + member_status + "]";
	}

	

	
	

}
