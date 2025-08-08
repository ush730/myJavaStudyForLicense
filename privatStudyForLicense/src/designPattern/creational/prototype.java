package designPattern.creational;

//Prototype: 인스턴스 객체의 내용을 그대로 복사하여 새로운 객체를 생성하는 방법을 제공하는 생성 패턴
//new를 사용하지 않고, 복사해서 객체를 생성한다.
//객체를 생성할 때 드는 비용을 줄이기 위해 사용한다.
//Prototype을 생성해 놓고, 나중에 복제해서 객체를 생성한다.
//생섵하는 객체가 동일하고 객체의 값이나 크기가 변하게 될 때 유용하다.
public class prototype {
	// 1. Prototype 인터페이스
	public interface Shape extends Cloneable {
	    Shape clone();  // 복제 메서드
	    void draw();
	}
	// 2. 구체 Prototype 클래스 - 원
	public static class Circle implements Shape {
	    private int x;
	    private int y;
	    private int radius;

	    public Circle(int x, int y, int radius) {
	        this.x = x;
	        this.y = y;
	        this.radius = radius;
	    }

	    // 복제 구현
	    @Override
	    public Shape clone() {
	        return new Circle(x, y, radius);
	    }

	    @Override
	    public void draw() {
	        System.out.println("⚪ 원 그리기: 중심(" + x + "," + y + "), 반지름 " + radius);
	    }
	}
	
	// 3. 구체 Prototype 클래스 - 사각형
	public static class Rectangle implements Shape {
	    private int width;
	    private int height;

	    public Rectangle(int width, int height) {
	        this.width = width;
	        this.height = height;
	    }

	    @Override
	    public Shape clone() {
	        return new Rectangle(width, height);
	    }

	    @Override
	    public void draw() {
	        System.out.println("⬜ 사각형 그리기: " + width + " x " + height);
	    }
	}
	
	// 4. Prototype을 사용하는 클라이언트
	public class Main {
	    public static void main(String[] args) {
	        // 원형(Prototype) 객체 생성
	        Shape circlePrototype = new Circle(10, 20, 5);
	        Shape rectanglePrototype = new Rectangle(8, 6);

	        // 복사본 생성
	        Shape clonedCircle = circlePrototype.clone();
	        Shape clonedRectangle = rectanglePrototype.clone();

	        // 출력
	        circlePrototype.draw();   // 원본
	        clonedCircle.draw();      // 복제본

	        rectanglePrototype.draw(); // 원본
	        clonedRectangle.draw();    // 복제본
	    }
	}
}
