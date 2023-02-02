package com.odw.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// 인터페이스를 구현 implements
public class MyFileRenamePolicy implements FileRenamePolicy {
	// FileRenamePolicy : 파일을 수정시키는 객체
	// rename()호출하면서 파일명 수정

	// 인터페이스의 특징 (추상화, 상수) -> 추상화를 실체화 시켜야함
	
	// 반드시 미완성된 rename이라는 추상메소드를 오버라이딩해서 구현해야함
	
	// 오버라이딩 
	public File rename(File originFile) { // 원본파일을 인자값으로 받았으니까 줘야한다.// 또 이름을 바꾼 파일을 파일로  돌려줘야하니까  
		
		// 원본파일명 뽑기  => 매개변수로 전달받은 원본 파일로부터 뽑아야한다
		String originName = originFile.getName();
		
		// 수정파일명 만들기(규칙)
		// 파일이 업로드된 시간(년월일시분초) + 5자리 랜덤값(10000~99999) + 확장자
		// 확장자 : 원본파일명 그대로(원본파일명에서 뽑아서
		
		// 수정명 : odw_20221130111800.jpg
		
		// 1. 파일업로드된 시간 추출 => String currentTime;
		// SimpleDateFormat활용
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 2. 5자리 랜덤값 => int ranNum;
		// Math 활용
		int ranNum = (int)(Math.random() * 90000) + 10000;
		
		// 3. 확장자 뽑기 => String ext
		// substring() + lastIndexOf(찾고자하는 문자열)
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 1 + 2 + 3조합해서 수정파일명을 변수에 담기
		String changeName = "odw_" + currentTime + "_" + ranNum + ext;
		
		// 기존파일을 수정된 파일명으로 적용시켜서 리턴
		return new File(originFile.getParent(), changeName);
	}
	
	
	
}
