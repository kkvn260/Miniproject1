package com.kosta.dao;

public class MemberDTO {
	
		private String id;
		private String name;
		private String pwd;
		private String email;

		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}

		public String getName() {
			return name;
		}
		public String getEmail() {
			return email;
		}

		public MemberDTO(String id, String name, String pwd, String email) {
			super();

			this.id = id;
			this.name = name;
			this.pwd = pwd;
			this.email = email;

		}
		public void setEmail(String email) {
			this.email = email;
		}
		public MemberDTO() {
			super();
		}
		public MemberDTO(String id2, String pwd2, String email2) {
		}
		
}
