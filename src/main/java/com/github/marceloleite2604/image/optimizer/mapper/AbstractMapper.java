package com.github.marceloleite2604.image.optimizer.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<I, O> implements Mapper<I, O> {

	@Override
	public Iterable<O> mapList(Iterable<I> iterableInput) {
		List<O> result = new ArrayList<>();
		for (I input : iterableInput) {
			result.add(map(input));
		}
		return result;
	}

}
