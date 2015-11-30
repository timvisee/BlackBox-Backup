package com.timvisee.blackbox.util;

import java.util.ArrayList;
import java.util.List;

import com.timvisee.blackbox.volume.Volume;

public class ListUtils {

	/**
	 * Move the item of a List one up
	 * @param items List with items
	 * @param itemIndex Item index to move one up
	 * @return New List
	 */
	public static <T> List<T> moveItemUp(List<T> items, int itemIndex) {
		return moveItem(items, itemIndex, itemIndex - 1);
	}
	
	/**
	 * Move the item of a List one up
	 * @param items List with items
	 * @param itemIndex Item index to move one down
	 * @return New List
	 */
	public static <T> List<T> moveItemDown(List<T> items, int itemIndex) {
		return moveItem(items, itemIndex, itemIndex + 1);
	}
	
	/**
	 * Move an item of a List to a different position
	 * @param items List with items
	 * @param itemIndex Item index to move
	 * @param newItemIndex Item index to move the item to
	 * @return New List
	 */
	public static <T> List<T> moveItem(List<T> items, int itemIndex, int newItemIndex) {
		// Make sure the items list is not null
		if(items == null)
			return null;
		
		// Make sure the list contains at least 2 items
		if(items.size() < 2)
			return items;
		
		// Make sure the index is not out of bounds
		if(itemIndex >= items.size() || newItemIndex >= items.size() ||
				itemIndex < 0 || newItemIndex < 0)
			return items;
		
		// Make sure the two indexes are different
		if(itemIndex == newItemIndex)
			return items;
		
		// Store all elements into a different list
		T i = items.get(itemIndex);
		List<T> others = new ArrayList<T>();
		others.addAll(cloneList(items));
		others.remove(itemIndex);
		
		// Build the the new list
		List<T> newList = new ArrayList<T>(items.size());
		newList.addAll(others.subList(0, newItemIndex));
		newList.add(i);
		newList.addAll(others.subList(newItemIndex, others.size()));
		
		// Return the new list
		return newList;
	}
	
	/**
	 * Clone a list
	 * Note: Function might become heavy with huge lists!
	 * @param list List to clone
	 * @return Cloned list
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> List<T> cloneList(List<T> list) {
	    return ((List<T>) ((ArrayList<T>) list).clone());
	}
	
	/**
	 * Clone a volume list
	 * Note: Function might become heavy with huge volume lists!
	 * @param v Volume list to clone
	 * @return Cloned volume list
	 */
	public static List<Volume> cloneVolumeList(List<Volume> v) {
		// Define the cloned list
		List<Volume> l = new ArrayList<Volume>();
		
		// Clone each volume and add it to the new list
		for(Volume entry : v)
			l.add(entry.clone());
		
		// Return the cloned list
		return l;
	}
}
