package designPattern.creational;

//추상 팩토리: 관련성을 갖는 객체 또는 독립적인 객체의 집합을 생성할 수 있는 인퍼테이스를 제공하는 생성 패턴
//여러 개의 연관된 서브 클래스를 특정 그룹으로 묶어 한 번에 수정할 수 있도록 만든 패턴
//생성 군들을 하나에 모아놓고 팩토리 중에서 선택하게 하는 패턴
//같은 종류이ㅡ 객체들을 일관된 방식으로 생성하는 경우에 유용
//관련이 있는 서브 클래스를 묶어서 팩토리 클래스로 만들고, 이들 조겅네 따라 객체를 생성하는 패턴
//여러 개의 클래스를 하나의 추상 클래스로 묶어 한 번에 교체할 수 있는 패턴
//제품, 운영체제, 회사별로 유사한 제품이지만 지원하는 기능에 차이가 있을 경우 기능별로 통합하여 운영할 때 유리
//Factory Method패턴을 확장한 캡슐화 방식
public class abstractFactory {
	
	//추상 제품
	public interface Header {
	    String getText();
	}
	public interface Footer {
	    String getText();
	}
	
	// 구체 제품
	public static class ModernHeader implements Header {
	    public String getText() {
	        return "=== Modern Document Header ===";
	    }
	}

	public static class ModernFooter implements Footer {
	    public String getText() {
	        return "=== Modern Document Footer ===";
	    }
	}

	public  static class ClassicHeader implements Header {
	    public String getText() {
	        return "*** Classic Header ***";
	    }
	}

	public static class ClassicFooter implements Footer {
	    public String getText() {
	        return "*** Classic Footer ***";
	    }
	}	
	
	//추상 팩토리 인터페이스
	public interface DocumentFactory {
	    Header createHeader();
	    Footer createFooter();
	}	
	
	//구체 팩토리 클래스
	public static class ModernDocumentFactory implements DocumentFactory {
	    public Header createHeader() {
	        return new ModernHeader();
	    }

	    public  Footer createFooter() {
	        return new ModernFooter();
	    }
	}

	public static class ClassicDocumentFactory implements DocumentFactory {
	    public Header createHeader() {
	        return new ClassicHeader();
	    }

	    public Footer createFooter() {
	        return new ClassicFooter();
	    }
	}
	
	//클라이언트 코드
	
	public static class DocumentRenderer {
	    private Header header;
	    private Footer footer;

	    public DocumentRenderer(DocumentFactory factory) {
	        this.header = factory.createHeader();
	        this.footer = factory.createFooter();
	    }

	    public void render() {
	        System.out.println(header.getText());
	        System.out.println("... 본문 내용 출력 ...");
	        System.out.println(footer.getText());
	    }
	}
	
	public class Main {
	    public static void main(String[] args) {
	        DocumentFactory factory;

	        String style = "classic"; // 또는 "modern"

	        if (style.equalsIgnoreCase("modern")) {
	            factory = new ModernDocumentFactory();
	        } else {
	            factory = new ClassicDocumentFactory();
	        }

	        DocumentRenderer renderer = new DocumentRenderer(factory);
	        renderer.render();
	    }
	}
	
}
