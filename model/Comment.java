package org.cl.model;

public class Comment
{
	private String created_at=null;	//���۴���ʱ��
	private String id=null;			//���۵�ID
	private String text=null;		//���۵�����
	private String user_id=null;	//�������ߵ��û�ID
	private boolean reply_comment=false;	//�����Ƿ���Դ���ۣ������������ڶ���һ���۵Ļظ�ʱ���ش��ֶ�

	/*
	 * {���۵�ID,�ַ����͵�����ID,���۵�MID,�����Ƿ���Դ����(false/true),���۵���Դ,���۵�����,�������ߵ��û�ID,���۴���ʱ��}
	 * */
	@Override
	public String toString()
	{
		return "{"+ id +
				" | " + reply_comment + 
				" | " + text +
				" | " + user_id + 
				" | " + created_at +
				"}";
	}
	
	public String getCreated_at()
	{
		return created_at;
	}
	public void setCreated_at(String createdAt)
	{
		created_at = createdAt;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		text=text.replaceAll("\\s+", " ");
		this.text = text;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String userId)
	{
		user_id = userId;
	}
	public boolean isReply_comment()
	{
		return reply_comment;
	}
	public void setReply_comment(boolean replyComment)
	{
		reply_comment = replyComment;
	}
}
