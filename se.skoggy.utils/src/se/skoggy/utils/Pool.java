package se.skoggy.utils;

public abstract class Pool<T> {

	public static final int DEFAULT_CAPACITY = 1024;
	protected int capacity;
	protected int count;
	
	public Pool(){
		this(DEFAULT_CAPACITY);
	}
	
	public Pool(int capacity){
		this.capacity = capacity;
		count = 0;
	}
	
	public void clear(){
		count = 0;
	}
	
	public int count(){
		return count;
	}
	public abstract T pop();
	public abstract void push(int index);
}
