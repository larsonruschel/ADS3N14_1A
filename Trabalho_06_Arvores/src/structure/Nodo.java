package structure;


// T - Valor Nodo 
public class Nodo<T, S> {
	// Attributes
	private Nodo<T, S> root;
	private Nodo<T, S> leftNode;
	private Nodo<T, S> rightNode;
	private S key;
	private T value;
	private int level;
	private int balance;
	private boolean black;
	
	// Construtor
	public Nodo()
	{
		root = null;
		leftNode = null;
		rightNode = null;
		key = null;
		value = null;
		level = -1;
		balance = 99;
		black = true;
	}
	
	public Nodo<T, S> getRoot() {
		return root;
	}
	public void setRoot(Nodo<T, S> root) {
		this.root = root;
	}
	public Nodo<T, S> getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(Nodo<T, S> leftNode) {
		this.leftNode = leftNode;
	}
	public Nodo<T, S> getRightNode() {
		return rightNode;
	}
	public void setRightNode(Nodo<T, S> rightNode) {
		this.rightNode = rightNode;
	}
	public S getKey() {
		return key;
	}
	public void setKey(S key) {
		this.key = key;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public boolean isBlack() {
		return black;
	}
	public void setBlack(boolean black) {
		this.black = black;
	}
}
