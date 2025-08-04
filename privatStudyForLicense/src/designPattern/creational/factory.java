
//팩토리 메소드(Factory Method): 인스턴스 생성과 관련된 결정을 서브클래스가 하도록 해서 객체 생성의 유연성을 극대화하는 생성 패턴
package designPattern.creational;

public class factory {
	
	public interface Button { //버튼 생성 인터페이스
	    void render();
	}
	
	public static class WindowsButton implements Button {
	    public void render() {
	        System.out.println("🪟 Windows 스타일 버튼");
	    }
	}

	public static class MacButton implements Button {
	    public void render() {
	        System.out.println("🍎 Mac 스타일 버튼");
	    }
	}
	
	public static abstract class Dialog {
	    // 팩토리 메서드
	    public abstract Button createButton();

	    // 공통 로직
	    public void renderWindow() {
	        Button btn = createButton();  // 구체적인 버튼 인스턴스 생성은 서브클래스가 담당
	        btn.render();
	    }
	}
	
	public static class WindowsDialog extends Dialog {
	    public Button createButton() {
	        return new WindowsButton();
	    }
	}

	public static class MacDialog extends Dialog {
	    public Button createButton() {
	        return new MacButton();
	    }
	}	

	public class Main { //클라이언트 코드
	    public static void main(String[] args) {
	        Dialog dialog;

	        String os = System.getProperty("os.name").toLowerCase();

	        if (os.contains("win")) {
	            dialog = new WindowsDialog();
	        } else {
	            dialog = new MacDialog();
	        }

	        dialog.renderWindow();  // 클라이언트는 버튼 생성 방식을 모름
	    }
	}
	
}
