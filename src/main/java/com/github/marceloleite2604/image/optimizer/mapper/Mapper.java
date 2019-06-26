package com.github.marceloleite2604.image.optimizer.mapper;

public interface Mapper<I, O> {

	public O map(I input);
	
	public Iterable<O> mapList(Iterable<I> iterableInput);
}
