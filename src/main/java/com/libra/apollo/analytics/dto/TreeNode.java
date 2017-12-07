package com.libra.apollo.analytics.dto;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {

	private T object;

	private TreeNode<T> parent;

	private List<TreeNode<T>> children = new ArrayList<>();

	public TreeNode(T object) {
		this.object = object;
	}

	public T getObject() {
		return object;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}

	public List<TreeNode<T>> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode<T>> children) {
		this.children = children;
	}

	public boolean contains(T t) {
		if (getObject().equals(t)) {
			return true;
		}
		for (TreeNode<T> node : getChildren()) {
			if (node.contains(t)) {
				return true;
			}
		}
		return false;
	}

}
