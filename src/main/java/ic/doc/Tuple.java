package ic.doc;

public class Tuple<A, B> {

  private final A elementA;
  private final B elementB;

  public Tuple(A elementA, B elementB) {
    this.elementA = elementA;
    this.elementB = elementB;
  }

  public A getElementA() {
    return elementA;
  }

  public B getElementB() {
    return elementB;
  }
}
