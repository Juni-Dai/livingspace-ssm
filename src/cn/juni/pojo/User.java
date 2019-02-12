package cn.juni.pojo;

public class User {

	private int uid;				//�û�ID
	private String uname;			//�û�����
	private String upwd;			//�û�����
	private int state;				//�û�״̬ 0���� 1����
	private int role;				//�û���ɫ 0����Ա 1��ͨ�û�
	
	public User() {}

	public User(int uid, String uname, String upwd, int state, int role) {
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
		this.state = state;
		this.role = role;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", state=" + state + ", role=" + role + "]";
	}
	
}
