package testPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class TestCal extends JFrame implements ActionListener{
	
	
	//memo기능 변수선언
	JPanel memoPanel;
	JButton btnAdd;
	JButton btnDel;
	JButton btnUpdate;
	int memoday=0;
	JLabel memodate;
	int c_date;
	int c_month;
	int c_year;
	String category;
	String catelist[]= {"business", "exam", "report", "family","friends"};
	int familymemoday, friendmemoday, businessmemoday, exammemoday, reportmemoday;
	JTextField memoArea;
	JLabel bottomInfo;
	JComboBox<String> cateCombo = new JComboBox<String>(catelist);//카테고리 콤보박스
	
	//dday기능 변수선언
	JButton whatToday;
	String s1=null;
	String s2=null;
	String s3=null;
	int cl_date;
	int cl_month;
	int cl_year;
	int Dyear;
	int Dmonth;
	int Dday;
	JPanel DDay=new JPanel();
	JButton inputs;
	JTextField whenY;
	JTextField whenM;
	JTextField whenD;
	JLabel DDayAnswer=new JLabel("D-day");

	

	
	//달력출력 변수 선언
	JButton dateButs[]; //임시버튼
	JButton days;
	String[] lunaholidays = {"0101", "0102", "0408", "0814", "0815", "0816"};
	String[] fours= {"0204","0219","0306","0321","0405","0420","0506","0521","0606","0622","0707","0723","0808","0823","0908","0923","1008","1024","1108","1122","1207","1222","0106","0120"};
	String[] foursname= {"입춘","우수","경칩","춘분","청명","곡우","입하","소만","망종","하지","소서","대서","입추","처서","백로","추분","한로","상강","입동","소설","대설","동지","소한","대한"};
	String[] holidays= {"0101","0301","0505","0512","0606","0815","1003","1009","1225"};
	String[] holidaysname= {"새해","삼일절","어린이날","부처님오신날","현충일","광복절","개천절","한글날","크리스마스"};
	String holi;
	String four;//휴일/절기
	JPanel centerPanel = new JPanel(new BorderLayout());//달력, 요일출력 패널
	JPanel titlePanel = new JPanel(new GridLayout(1, 7));//요일출력 패널
	String titleStr[] = {"일", "월", "화", "수", "목", "금", "토"};//요일
	JPanel datePanel = new JPanel(new GridLayout(0, 7));//달력출력 패널

	Calendar now; //오늘 날짜가 될 객체
	int year, month, date;	//오늘 날짜
	
	//topPanel
	JPanel topPanel = new JPanel();
	JButton prevBtn = new JButton("◀");
	JButton nextBtn = new JButton("▶");
		
	JLabel yearLbl = new JLabel("년");
	JLabel monthLbl = new JLabel("월");
		
	

	JComboBox<Integer> yearCombo = new JComboBox<Integer>();

	DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();

	JComboBox<Integer> monthCombo = new JComboBox<Integer>();

	DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
	//년,월 콤보박스



	
//생성자
	public TestCal() {
		setTitle("IT정보공학과 201812788 양건희");
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//자원 해제 후 종료

		now = Calendar.getInstance();	//오늘 날짜 객체

		year = now.get(Calendar.YEAR);

		month = now.get(Calendar.MONTH)+1;

		date = now.get(Calendar.DATE);  //오늘/년/월/일

		
//topPanel 만들기!
		topPanel.add(new JLabel("today:"+year+"년 "+month+"월 "+date+"일"));//오늘 날 출력
		
		topPanel.add(prevBtn);//이전 달로 넘어감
		

		for(int i=year-2019; i<=year+2000; i++){
			yearModel.addElement(i); //년도 콤보박스에 넣을 년도 범위 정함
		}

		yearCombo.setModel(yearModel);//콤보박스에 년도 범위 넣음

		yearCombo.setSelectedItem(year);	//현재 년도 선택

		topPanel.add(yearCombo);

		
	
		topPanel.add(yearLbl);


		for(int i=1; i<=12; i++){
			monthModel.addElement(i);//월 콤보박스에 넣을 월 범위 정함 1-12
		}

		monthCombo.setModel(monthModel);//콤보박스에 월 넣음

		monthCombo.setSelectedItem(month);	//현재 월 선택

		topPanel.add(monthCombo);
		topPanel.add(monthLbl);
		topPanel.add(nextBtn);//다음 달 버튼

		
//CenterPanel만들기
		
		//요일 titlePanel 만들기
		titlePanel.setBackground(Color.white);

		for(int i=0; i<titleStr.length; i++){//요일 넣기

			JButton dow=new JButton(titleStr[i]);
			dow.setBackground(Color.white);

			if(i == 0){//일요일=0 빨간색

				dow.setForeground(Color.red);

			}else if(i == 6){//토요일=6 파란색

				dow.setForeground(Color.blue);

			}

			titlePanel.add(dow);

		}
	
		centerPanel.add(titlePanel, "North");
		
		//datePanel 만들기

		dayPrint(year, month);//날짜출력함수
		centerPanel.add(datePanel, "Center");

		

		

		//memoPanel 만들기
		
		c_date=date;
		c_month=month;  //메모할 날짜 초기값=오늘
		c_year=year;
			memoPanel=new JPanel();
			memoPanel.setLayout(new BorderLayout());
						//배치 관리자, north에 버튼 center에 메모창 south에 알림창
			btnAdd=new JButton("메모추가"); 
			btnDel=new JButton("메모삭제");
			btnUpdate=new JButton("메모수정");
			memoArea=new JTextField();  //메모 넣을 텍스트 필드
			bottomInfo=new JLabel();   //잘 실행됬는지 확인하는 문장 레이블
			memoArea.setSize(150,150);
			memodate=new JLabel("메모할날짜:"+c_year+"/"+c_month+"/"+c_date);
			JPanel btnPanel=new JPanel();
			cateCombo.setSelectedIndex(0);
			category=(String)cateCombo.getSelectedItem(); //변수 category=콤보박스 되어있는 문자열
			btnPanel.add(cateCombo);
			cateCombo.addActionListener(e->{category=(String)cateCombo.getSelectedItem();
			readMemo();});//콤보박스에서 선택시 변수 category에 저장, 저장된 메모 출력
			
			
			btnPanel.add(btnAdd);
			btnPanel.add(btnDel);
			btnPanel.add(btnUpdate);
			btnPanel.add(memodate);
			memoPanel.add(btnPanel,BorderLayout.NORTH);
			memoPanel.add(memoArea, BorderLayout.CENTER);
			memoPanel.add(bottomInfo, BorderLayout.SOUTH);			
		
			
		//d-day기능 Panel 만들기
			
		whatToday=new JButton("오늘부터는?"); //오늘로부터 버튼
		inputs=new JButton("날짜입력"); //계산 버튼
		whenY=new JTextField(10);	//알아볼 년/월/일 입력
		whenM=new JTextField(10);
		whenD=new JTextField(10);
		cl_date=date;
		cl_month=month; //d-day 초기값 오늘
		cl_year=year;
		whatToday.addActionListener(this);
		inputs.addActionListener(this);
		DDay.setLayout(new GridLayout(0,7));
		
		DDay.add(whenY);
		DDay.add(whenM);
		DDay.add(whenD);
		DDay.add(inputs);
		DDay.add(whatToday);
		DDay.add(DDayAnswer); //panel들 만들기 끝
		
		
		add(topPanel,BorderLayout.NORTH);
		add(DDay, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		add(memoPanel, BorderLayout.LINE_END);
		
		setSize(1100,500);
		whatToday.setBackground(Color.white);
		btnAdd.setBackground(Color.white);
		btnDel.setBackground(Color.white);
		btnUpdate.setBackground(Color.white);
		inputs.setBackground(Color.white);
		
		setVisible(true);

		
	
		prevBtn.addActionListener(this);

		nextBtn.addActionListener(this);

		yearCombo.addActionListener(e->{createDayStart();});

		monthCombo.addActionListener(e->{createDayStart();});
		
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		btnUpdate.addActionListener(this);

		

	}//생성자 끝
	
	

	
	public int getDDay(int y, int m, int d) {//D-day 출력 함수
		Calendar doday=Calendar.getInstance();
		Calendar d_day=Calendar.getInstance();
		d_day.set(y, m-1, d); //검색한 날짜로 객체 설정
		doday.set(cl_year,cl_month-1,cl_date); //선택한 날짜로 객체 설정, 초기값 오늘
		long l_dday=d_day.getTimeInMillis()/(24*60*60*1000);
		long l_doday=doday.getTimeInMillis()/(24*60*60*1000);
		
		long dDay=l_doday-l_dday;
		
		return (int)dDay; //남은 날짜 계산한 값 출력
	}

	
	public void actionPerformed(ActionEvent e){//액션리스너 함수
		
			if (e.getSource()==btnAdd) {//저장버튼눌렀을때
				  save();     //저장함수
			}
		
			else if (e.getSource()==btnDel) {//삭제버튼
			      memoArea.setText("");
			      File f=new File(c_year+"_"+((c_month)<10? "0":"")+(c_month)+"_"+(c_date<10? "0":"")+c_date+"_"+category+".txt");
	              if (f.exists()) {//해당 파일 존재시 이프문 실행
	            	  f.delete();//해당 파일 삭제
	            	  createDayStart();//달력 재출력
	            	  bottomInfo.setText(c_year+"_"+((c_month)<10? "0":"")+(c_month)+"_"+(c_date<10? "0":"")+c_date+"_"+category+".txt"+"를 삭제했다");
	              }
	              else bottomInfo.setText(null);//파일이 없다면 알림창 null
			}
			
			else if (e.getSource()==btnUpdate) {//수정버튼
				
				update();//수정함수
				
			}
			
			
			else if (e.getSource()==inputs) {//d-day 입력 버튼
				s1=whenY.getText();
				s2=whenM.getText(); //텍스트필드에서 검색한 날짜 가져오기(문자열)
				s3=whenD.getText();
				Dyear=Integer.parseInt(s1);
				Dmonth=Integer.parseInt(s2); //검색한 날짜 int로 변환
				Dday=Integer.parseInt(s3);
				
			if ((getDDay(Dyear, Dmonth, Dday))>0) 
				DDayAnswer.setText("D+"+(getDDay(Dyear, Dmonth, Dday)));
			//계산한 값 띄우기, 검색한 날짜가 오늘보다 이전이면 D+
			
					
				else
				DDayAnswer.setText("D"+(getDDay(Dyear, Dmonth, Dday)));
			//검색 날이 오늘보다 이후이면 D-
			}
			
			
			else if (e.getSource()==whatToday) {//d-day오늘부터는?버튼
				cl_date=date; 
				cl_month=month;//오늘로 변환
				cl_year=year;
				if (s1!=null && s2!=null && s3!=null) {//검색한 날짜 있을때
					if ((getDDay(Dyear, Dmonth, Dday))>0) 
						DDayAnswer.setText("D+"+(getDDay(Dyear, Dmonth, Dday)));
				
						
					else
						DDayAnswer.setText("D"+(getDDay(Dyear, Dmonth, Dday)));
				}
			
			}
			
			else if (e.getSource()==prevBtn || e.getSource()==nextBtn) {//이전달, 다음달 넘어가기
			
			int yy = (Integer)yearCombo.getSelectedItem();

			int mm = (Integer)monthCombo.getSelectedItem();

			if(e.getSource()==prevBtn){	//전달

				if(mm==1){

					yy--; mm=12; //이전해로 넘어가기

				}else{

					mm--;

				}				

			}else if(e.getSource()==nextBtn){	//다음달

				if(mm==12){

					yy++; mm=1; //다음해로 넘어가기

				}else{

					mm++;

				}

			}
			
			
			
			yearCombo.setSelectedItem(yy);

			monthCombo.setSelectedItem(mm);

			}
		
		
		else { //날짜 누를때
			for (int i=1;i<=31;i++) {
				if (e.getSource()==dateButs[i-1]) {//날짜 누를때(메모 보이기, d-day보이기)
					memoArea.setText(null);
					c_date=i;
					c_year=(Integer)yearCombo.getSelectedItem();
					c_month=(Integer)monthCombo.getSelectedItem();
					cl_date=c_date;
					cl_year=c_year;
					cl_month=c_month; //누른 날짜로 메모날, DDAY 날 변경
					memodate.setText("메모할날짜:"+c_year+"/"+c_month+"/"+c_date);
					readMemo(); //누른 날의 메모 읽기 함수
					if (s1!=null && s2!=null && s3!=null) {
						if ((getDDay(Dyear, Dmonth, Dday))>0) 
							DDayAnswer.setText("D+"+(getDDay(Dyear, Dmonth, Dday)));	
							
						else
							DDayAnswer.setText("D"+(getDDay(Dyear, Dmonth, Dday)));
					}
					
				}
			}
			
		}
			
			
	
		

	}//액션리스너 정의 끝
	
	
	public void save() {//메모저장함수
		try {
			String memo=memoArea.getText();
			if (memo.length()>0) {
				FileWriter outt=new FileWriter(c_year+"_"+((c_month)<10? "0":"")+(c_month)+"_"+(c_date<10? "0":"")+c_date+"_"+category+".txt");
				BufferedWriter out=new BufferedWriter(outt);
				//해당 날짜, 카테고리 이름 파일 생성
				String str = memoArea.getText();
				out.write(str);//파일에 문자열 출력
				out.close();
				createDayStart(); //달력 재출력(메모유무 표시)
				bottomInfo.setText(c_year+"_"+((c_month)<10? "0":"")+(c_month)+"_"+(c_date<10? "0":"")+c_date+"_"+category+".txt"+"를 저장.");
			}
			
			else bottomInfo.setText("스케줄을 먼저 작성해주세요");
			
			
		}catch (IOException e) {
			bottomInfo.setText("error 스케줄작성 실패"); //예외 처리
		}
		
	}
	
	public void update() {//메모수정함수//사실상 저장함수와 기능은 비슷하다.
		
		try{
			FileWriter outt=new FileWriter(c_year+"_"+((c_month)<10? "0":"")+(c_month)+"_"+(c_date<10? "0":"")+c_date+"_"+category+".txt");
			  BufferedWriter out=new BufferedWriter(outt);
			  String str=memoArea.getText();
			  out.write(str);
			  out.close();
			  createDayStart();
			  bottomInfo.setText(c_year+"_"+((c_month)<10? "0":"")+(c_month)+"_"+(c_date<10? "0":"")+c_date+"_"+category+".txt"+"수정완료");
	
		}
		catch (IOException e) {
		bottomInfo.setText("error 스케줄작성 실패");}//예외처리
	
		}
		
	
	public void readMemo(){//메모보이기 함수
		try{ 	
			memoArea.setText(null);
			FileReader in =new FileReader(c_year+"_"+((c_month)<10? "0":"")+(c_month)+"_"+(c_date<10? "0":"")+c_date+"_"+category+".txt");
			BufferedReader out=new BufferedReader(in);
			//읽을 파일
			String memoAreaText=new String();
			int i;
			while ((i=out.read())!=-1) {
				memoAreaText=memoAreaText+(char)i;//한글자씩 불러와 문자열에 저장하기
			}
			memoArea.setText(memoAreaText);
			in.close();
		
	}catch(IOException e) {
		e.printStackTrace();
		bottomInfo.setText("입력된 스케줄이 없습니다.");//예외처리
	
	}
	}

	
	public void createDayStart(){//달력 보이기

		datePanel.setVisible(false);	//패널 숨기기

		datePanel.removeAll();	//날짜 출력한 라벨 지우기

		dayPrint((Integer)yearCombo.getSelectedItem(), (Integer)monthCombo.getSelectedItem());

		datePanel.setVisible(true);	//패널 재출력				

	}
	
	
	
    public void checkday(int y, int m, int d){//메모 저장되어있는 날짜 체크
       
         try{ // 각 카테고리 파일이 있다면 categorymemoday=1 설정
        	 //이후 1인 카테고리를 달력에 표시할 것임.
        	 File fpn =new File(y+"_"+((m)<10? "0":"")+(m)+"_"+(d<10? "0":"")+d+"_"+"exam"+".txt");
         	if (fpn.exists()) {
         		exammemoday=1;
         	}
                 else exammemoday=0;
         	File fi =new File(y+"_"+((m)<10? "0":"")+(m)+"_"+(d<10? "0":"")+d+"_"+"report"+".txt");
        	if (fi.exists()) {
        		reportmemoday=1;
        	}
                else reportmemoday=0;
        	File fin =new File(y+"_"+((m)<10? "0":"")+(m)+"_"+(d<10? "0":"")+d+"_"+"business"+".txt");
        	if (fin.exists()) {
        		businessmemoday=1;
        	}
                else businessmemoday=0;
        
        	File fon =new File(y+"_"+((m)<10? "0":"")+(m)+"_"+(d<10? "0":"")+d+"_"+"family"+".txt");
        	if (fon.exists()) {
        		familymemoday=1;
        	}
                else familymemoday=0;
      
        
        	File fen =new File(y+"_"+((m)<10? "0":"")+(m)+"_"+(d<10? "0":"")+d+"_"+"friends"+".txt");
        	if (fen.exists()) {
        		friendmemoday=1;
        	}
                else friendmemoday=0;
            
          
         }
        
         catch(Exception e)
         {
                e.printStackTrace();
         }
}
    
    

	public void dayPrint(int y, int m){//달력 출력함수

		Calendar cal = Calendar.getInstance();

		cal.set(y, m-1, 1);	//출력할 날 객체

		int week = cal.get(Calendar.DAY_OF_WEEK);	//1일에 대한 요일	일요일 : 0 

		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);	//그 달의 마지막 날

		for(int i=1; i<week; i++){	//날짜 출력 전 공백출력

			datePanel.add(new JLabel(" "));

		}
		
		dateButs=new JButton[lastDate]; //버튼 누르면 해당 날짜로 메모/d-day 하기위해 만든 임시버튼
		
		for(int i=1; i<=lastDate; i++){
			String dayname;
			
			days=new JButton(""+i);
			dateButs[i-1]=days;  //버튼 days=버튼배열 -> 버튼배열에 액션리스너 넣기위함
				//days는 날짜마다 계속 새롭게 생성되기 때문에 액션리스너를 넣을 수 없음
			dateButs[i-1].addActionListener(this);
					
			
			days.setLayout(new BorderLayout());
			
			cal.set(y, m-1, i); 
			
			int outWeek = cal.get(Calendar.DAY_OF_WEEK);//해당 날짜의 요일알아봄(일요일 =1 월요일=2.....토요일=7

			if(outWeek==1){ //일요일 빨강

				days.setForeground(Color.red);				

			}else if(outWeek==7){ //토요일 파랑

				days.setForeground(Color.BLUE);

			}
			//휴일표시
			if (m<10) { //날짜를 문자열로 바꾼다. 휴일/절기 문자열배열과 동일한 형태로.
				if (i<10)
					dayname="0"+m+"0"+i;
				else
					dayname="0"+m+i;
				}
			else {
				if (i<10)
					dayname=m+"0"+i;
				else
					dayname=m+""+i; 
			}
			holi=dayname;
			four=dayname;
			
			
			Font f1, f2;
			f1=new Font("돋움", Font.PLAIN, 10);
			f2=new Font("돋움", Font.PLAIN, 13);
		
	
			
			for (int k=0;k<24;k++) {
				if (four.equals(fours[k])) {
					JLabel aa=new JLabel(foursname[k]); //절기 레이블 넣기
					aa.setFont(f1);
					days.add(aa, BorderLayout.LINE_END);
					break;
				}}
			
			for (int k=0;k<9;k++) {
				if (holi.equals(holidays[k])) {
					days.setForeground(Color.red); //공휴일 빨간색으로 표시
					JLabel aak=new JLabel(holidaysname[k]); // 공휴일 레이블 넣기
					aak.setFont(f1);
					days.add(aak, BorderLayout.PAGE_END);
					
							
					break;
				}
			}
			//휴일 표시 끝
			
			//메모 되있는 부분 표시
			String marker="";
			JLabel mark=new JLabel("");
			
			businessmemoday=0;
			familymemoday=0;
			friendmemoday=0;
			exammemoday=0;
			reportmemoday=0; 
			
			checkday(y, m, i); //checkday함수에서 각 memoday=1(메모 유)/0(메모 무)구분
			
			if (exammemoday == 1)
				marker=marker+"E";
			if (reportmemoday == 1)
				marker=marker+"R";
			if (businessmemoday == 1) 
				marker=marker+" B";
			if (familymemoday == 1)
				marker=marker+" FA";				
			if (friendmemoday == 1) 
				marker=marker+" FR";   //각 카테고리 앞글자 따서 표시

			mark.setText(marker);		
			mark.setForeground(Color.ORANGE);
			days.add(mark, BorderLayout.PAGE_START);
			//메모유무표시 끝
			
			
			days.setBackground(Color.white);
		
			datePanel.add(days);

		}

	}	

	public static void main(String[] args) {

		TestCal MySchedular=new TestCal(); //생성자 호출



	}



}
