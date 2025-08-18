package designPattern.Structural;

/*브리지: 하나의 클래스 계층에 복잡하게 존재하는 기능 클래스와 구현 클래스를 분리하여 두 개의 클래스를 ㅇ녀결하여 사용하는 것
추상과 구현ㅇ르 분리하여 결합도를 낮춘 패턴
기능 클래스와 구현 클래스를 연결
기능과 구현을 구분하여 필요한 부분을 독립적으로 추가할 수 있음
Extends는 견고한 여결이고 Bridge는 느슨한 연결
프로그램의 필요에 따라서 클래스 간의 관계를 손쉽게 바꾸고 싶을 때는 브리지 패턴을 사용*/
public class bridge {
	 public static void main(String[] args) {
	        Device tv = new Tv();
	        Remote basic = new BasicRemote(tv);

	        System.out.println("== BasicRemote + TV ==");
	        basic.togglePower();
	        basic.volumeUp();
	        basic.volumeUp();
	        basic.channelUp();

	        System.out.println();

	        Device radio = new Radio();
	        AdvancedRemote advanced = new AdvancedRemote(radio);

	        System.out.println("== AdvancedRemote + Radio ==");
	        advanced.togglePower();
	        advanced.volumeUp();
	        advanced.mute();
	        advanced.channelDown();
	    }
	}

	/* -------- 구현 계층(Implementor) -------- */
	interface Device {
	    boolean isEnabled();
	    void enable();
	    void disable();

	    int getVolume();           // 0 ~ 100
	    void setVolume(int percent);

	    int getChannel();          // 1 이상
	    void setChannel(int channel);

	    String name();             // 로그용
	}

	/* 구체 구현체(Concrete Implementor) - TV */
	class Tv implements Device {
	    private boolean on = false;
	    private int volume = 30;
	    private int channel = 1;

	    @Override public boolean isEnabled() { return on; }
	    @Override public void enable() { on = true; System.out.println("[TV] power ON"); }
	    @Override public void disable() { on = false; System.out.println("[TV] power OFF"); }

	    @Override public int getVolume() { return volume; }
	    @Override public void setVolume(int percent) {
	        volume = clamp(percent, 0, 100);
	        System.out.println("[TV] volume = " + volume);
	    }

	    @Override public int getChannel() { return channel; }
	    @Override public void setChannel(int channel) {
	        this.channel = Math.max(1, channel);
	        System.out.println("[TV] channel = " + this.channel);
	    }

	    @Override public String name() { return "TV"; }

	    private static int clamp(int v, int min, int max) {
	        return Math.max(min, Math.min(max, v));
	    }
	}

	/* 구체 구현체(Concrete Implementor) - Radio */
	class Radio implements Device {
	    private boolean on = false;
	    private int volume = 50;
	    private int channel = 90; // FM 90.0 처럼 가정

	    @Override public boolean isEnabled() { return on; }
	    @Override public void enable() { on = true; System.out.println("[Radio] power ON"); }
	    @Override public void disable() { on = false; System.out.println("[Radio] power OFF"); }

	    @Override public int getVolume() { return volume; }
	    @Override public void setVolume(int percent) {
	        volume = clamp(percent, 0, 100);
	        System.out.println("[Radio] volume = " + volume);
	    }

	    @Override public int getChannel() { return channel; }
	    @Override public void setChannel(int channel) {
	        this.channel = Math.max(1, channel);
	        System.out.println("[Radio] channel = " + this.channel);
	    }

	    @Override public String name() { return "Radio"; }

	    private static int clamp(int v, int min, int max) {
	        return Math.max(min, Math.min(max, v));
	    }
	}

	/* -------- 추상 계층(Abstraction) -------- */
	interface Remote {
	    void togglePower();
	    void volumeUp();
	    void volumeDown();
	    void channelUp();
	    void channelDown();
	}

	/* 세부 추상(Refined Abstraction) - 기본 리모컨 */
	class BasicRemote implements Remote {
	    protected final Device device;

	    public BasicRemote(Device device) {
	        this.device = device;
	    }

	    @Override public void togglePower() {
	        if (device.isEnabled()) device.disable();
	        else device.enable();
	    }

	    @Override public void volumeUp() {
	        device.setVolume(device.getVolume() + 10);
	    }

	    @Override public void volumeDown() {
	        device.setVolume(device.getVolume() - 10);
	    }

	    @Override public void channelUp() {
	        device.setChannel(device.getChannel() + 1);
	    }

	    @Override public void channelDown() {
	        device.setChannel(device.getChannel() - 1);
	    }
	}

	/* 세부 추상(Refined Abstraction) - 고급 리모컨(추가 기능) */
	class AdvancedRemote extends BasicRemote {
	    public AdvancedRemote(Device device) {
	        super(device);
	    }

	    public void mute() {
	        System.out.println("** MUTE on " + device.name() + " **");
	        device.setVolume(0);
	    }
}
