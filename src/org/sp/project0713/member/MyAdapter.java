package org.sp.project0713.member;
import java.awt.event.*;

/*
java GUI �о߿����� Adapter��?
�����ڰ� �������̽� ������ �ش� �������̽��� ������ �޼��尡
�ʹ��� ���� ��� ��������� �ʴ� �޼��带 �������̵� �ؾ� �Ѵٴ�
��Ģ�� ����ϸ� ������ ȿ������ ��������.
�� ������ �ذ��ϱ� ���� ������ ��� sun���� Adapter��� Ŭ������
������ �ִµ� ����ʹ� �츮�� �����ؾ��� �������̽��� ��� ������ �������Ƿ�
�����ڴ� ������� �޼��� �� �ʿ��� �͸� ��� �������̵��ϸ� �ȴ�.
*/
public class MyAdapter extends WindowAdapter{

	

	public void windowClosing(WindowEvent e){
		//1)�����ִ� Connection �ݱ�
		con.close();
		//2)���μ����� ����
	}
}
