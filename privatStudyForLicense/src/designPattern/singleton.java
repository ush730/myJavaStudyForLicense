package designPattern;

//싱글톤: 클래스의 인스턴스가 단 하나임을 보장하면서 해당 인스턴르소의 접근 방법을 제공하는 패턴. 생성 패턴에 속함
public class singleton {
	
    // 1. 자신의 클래스 내부에 static으로 유일한 인스턴스를 생성
    private static singleton instance = new  singleton();

    // 2. 생성자를 private으로 선언하여 외부에서 생성 못하도록 함
    private  singleton() {
        System.out.println("싱글톤 인스턴스 생성됨");
    }

    // 3. 외부에서 인스턴스를 얻을 수 있는 정적 메서드 제공
    public static  singleton getInstance() {
        return instance;
    }

    // 테스트용 메서드
    public void showMessage() {
        System.out.println("안녕하세요, 싱글톤입니다!");
    }
    
    public static void main(String[] args) {
    	singleton s1 = singleton.getInstance();
    	singleton s2 = singleton.getInstance();

        s1.showMessage();

        // 두 인스턴스는 동일한 객체인가?
        System.out.println(s1 == s2);  // true 출력
    }
}
