package step15.ex03;

public class Test01 {

	public static void main(String[] args) {
		try {
			m1();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			//정상적으로 실행했든 오류가 발생했든 무조건 실행하는 블록
			// => try 또는 catch 블록을 빠져나가기 전에 무조건 실행한다.
			// => 용도 : 마무리 작업, 즉 , 사용했던 자원을 해제하는 코드,
			//					예) 파일을 닫는다. => f.close();
			System.out.println("정상적으로 실행을 완료했다.");
		}

	}
	
	public static void m1() throws Exception {
		throw new Exception("오류 발생!");
	}
}
