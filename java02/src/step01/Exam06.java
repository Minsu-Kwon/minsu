/*
 * 주제 : 리터럴 - 실수
 * - 부동소수점 표현하기
 * - 3.14 처럼 그냥 표현하면 8바이트 부동소수점값이 된다.
 * 	 3.14f 나 3.14F로 표현하면 4바이트 부동소수점 값이된다.
 */

package step01;

public class Exam06 {
	public static void main(String[] args) {
		System.out.println(3.14);
		System.out.println(31.4E-1);	// 대문자 E를 주로 사용. 31.4 * 10^-1
		System.out.println(31.4e-1);
		System.out.println(314E-2);	// 314 * 10^-2 = 314 * 1/100
		System.out.println(0.314E1);	// 0.314 * 10
		System.out.println(0.0314E2);	// 0.0314 * 100
		
		System.out.println(3.14F);	// 4byte 부동소수점
		System.out.println(3.14f);	// 소문자 주로사용
	}
}
