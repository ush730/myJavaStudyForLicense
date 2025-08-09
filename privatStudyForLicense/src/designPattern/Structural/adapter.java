package designPattern.Structural;

/*어댑터: 호환성이 없는 객체 간 인퍼테이스를 이용해 작동하게 해 주는 패턴
인터페이스로 인해 함께 사용하지 못하는 클래스를 함께 사용하도록 하는 패턴
호환성이 없는 인터페이스 때문에 함께 동작할 수 없는 클래스들을 함께 동락하하도록 해준다.*/
public class adapter {
	// Target: 클라이언트가 기대하는 인터페이스
	interface Shape {
	    void draw(int x, int y, int width, int height);
	}

	// Adaptee: 호환 안 되는 레거시 인터페이스
	static class LegacyRectangle {
	    // 좌상단(x1,y1)과 우하단(x2,y2) 좌표로 그린다
	    public void draw(int x1, int y1, int x2, int y2) {
	        System.out.printf("LegacyRectangle.draw -> (%d,%d) to (%d,%d)%n", x1, y1, x2, y2);
	    }
	}

	// Adapter: Target을 구현하고, 내부에서 Adaptee를 변환해 호출
	static class RectangleAdapter implements Shape {
	    private final LegacyRectangle adaptee = new LegacyRectangle();

	    @Override
	    public void draw(int x, int y, int width, int height) {
	        int x1 = x;
	        int y1 = y;
	        int x2 = x + width;
	        int y2 = y + height;
	        adaptee.draw(x1, y1, x2, y2);
	    }
	}

	// Client
	public class AdapterDemo {
	    public static void main(String[] args) {
	        Shape rect = new RectangleAdapter();
	        rect.draw(10, 20, 100, 40); // 기대 인터페이스대로 사용
	    }
	}
}
