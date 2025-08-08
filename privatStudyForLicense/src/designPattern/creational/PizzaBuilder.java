package designPattern.creational;

//빌더: 복잡한 객체 생성 방법을 별도로 캡슐화하여 구현 시 동일하나 과정으로 다양한 형태의 합성 객체를 얻을 수 있게 해주는 패턴
//객체의 생성에 있어서 복잡한 과정들을 분류해낸다.
//복잡한 객체를 단계적으로 생성하는 것에 초점ㅇ르 둔다.
//많은 인수를 가진 객체를 생성할 때 다른 객체의 도움으로 생성하는 패턴이다.
//데이터의 순서에 상관없이 객체를 만들어낸다.
//Builder 패턴은 생성자 초기화 문제 때문에 만들어졌다.

public class PizzaBuilder {
    // --- 필수 ---
    private final Size size;

    // --- 선택 ---
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean olives;
    private final boolean extraSauce;

    // 외부에서 new 못 하게 private
    private PizzaBuilder(Builder b) {
        this.size = b.size;
        this.cheese = b.cheese;
        this.pepperoni = b.pepperoni;
        this.olives = b.olives;
        this.extraSauce = b.extraSauce;
    }

    public enum Size { SMALL, MEDIUM, LARGE }

    // -------- Builder --------
    public static class Builder {
        // 필수 파라미터
        private final Size size;

        // 선택 파라미터 (기본값)
        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean olives = false;
        private boolean extraSauce = false;

        public Builder(Size size) {
            if (size == null) throw new IllegalArgumentException("size is required");
            this.size = size;
        }

        public Builder cheese(boolean v) { this.cheese = v; return this; }
        public Builder pepperoni(boolean v) { this.pepperoni = v; return this; }
        public Builder olives(boolean v) { this.olives = v; return this; }
        public Builder extraSauce(boolean v) { this.extraSauce = v; return this; }

        public PizzaBuilder build() {
            // 추가 검증 로직 가능 (예: LARGE는 소스 필수 등)
            return new PizzaBuilder(this);
        }
    }

    @Override
    public String toString() {
        return "PizzaBuilder{" +
                "size=" + size +
                ", cheese=" + cheese +
                ", pepperoni=" + pepperoni +
                ", olives=" + olives +
                ", extraSauce=" + extraSauce +
                '}';
    }
    
    public class Main {
        public static void main(String[] args) {
            PizzaBuilder margherita = new PizzaBuilder.Builder(PizzaBuilder.Size.MEDIUM)
                    .cheese(true)
                    .olives(true)
                    .build();

            PizzaBuilder meatLovers = new PizzaBuilder.Builder(PizzaBuilder.Size.LARGE)
                    .cheese(true)
                    .pepperoni(true)
                    .extraSauce(true)
                    .build();

            System.out.println(margherita);
            System.out.println(meatLovers);
        }
    }
}


