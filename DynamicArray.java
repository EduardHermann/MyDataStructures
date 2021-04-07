package pack;

import java.util.Iterator;

/**
 * This is my implementation of a dynamic array.
 *
 * @author EduardHermann
 * @version 1.0
 * @since 07.04.2021
 */
public class DynamicArray<T> implements Iterable<T> {

  public T[] array;
  private int length = 0;
  private int capacity = 0;

  /**
   * Whenever creating a "DynamicArray" the starting length must be defined.
   * 
   * @param capacity: starting length
   */
  @SuppressWarnings("unchecked")
  public DynamicArray(int length) {
    if (length >= 0) {
      this.length = length;
      this.capacity = length;
      array = (T[]) new Object[length];
    } else {
      throw new IllegalArgumentException("The length of the dynamic array must be at least 0!");
    }
  }

  /**
   * This method returns the current length of the dynamic array.
   * 
   * @return: current length
   */
  public int getLength() {
    return capacity;
  }

  /**
   * This method returns an object in the dynamic array via a valid index.
   * 
   * @param index: valid index
   * @return: object
   */
  public T getObject(int index) {
    if (index >= 0 && index < capacity) {
      return array[index];
    } else {
      throw new IndexOutOfBoundsException(
          "The given index doesnt match the length of the dynamic array!");
    }
  }

  /**
   * This method sets the content at a valid index in the dynamic array.
   * 
   * @param index: valid index
   * @param object: object
   */
  public void setObject(int index, T object) {
    if (index >= 0 && index < capacity) {
      array[index] = object;
    } else {
      throw new IndexOutOfBoundsException(
          "The given index doesnt match the length of the dynamic array!");
    }
  }

  /**
   * This method checks whether the dynamic array in currently empty or not.
   * 
   * @return: true or false based on the array condition
   */
  public boolean isEmpty() {
    if (capacity == 0) {
      return true;
    }
    return false;
  }

  /**
   * This method appends an object at the end of the dynamic array.
   * 
   * @param object: appending object
   */
  public void append(T object) {
    if (length > capacity) {
      capacity++;
      setObject(capacity - 1, object);
    } else {
      expandDynamicArray();
      append(object);
    }
  }

  /**
   * This method appends an array at the end of the dynamic array.
   * 
   * @param objects: appending array
   */
  public void appendArray(T[] objects) {
    if ((length - capacity) >= objects.length) {
      for (int i = 0; i < objects.length; i++) {
        append(objects[i]);
      }
    } else {
      expandDynamicArray();
      appendArray(objects);
    }
  }

  /**
   * This method removes the last object of the dynamic array.
   */
  public void remove() {
    if (capacity >= 1) {
      array[capacity - 1] = null;
      capacity--;
    }
  }

  /**
   * This method removes an object at a specific index of the dynamic array.
   * 
   * @param index: specific index
   */
  public void removeAt(int index) {
    if (index >= 0 && index < capacity) {
      array[index] = null;
      for (int i = index; i < capacity; i++) {
        array[i] = array[i + 1];
        array[i + 1] = null;
      }
      capacity--;
    } else {
      throw new IndexOutOfBoundsException(
          "The given index doesnt match the capacity of the dynamic array!");
    }
  }

  /**
   * This method decreases the length of the dynamic array.
   * 
   * @param capacity: new length
   */
  public void trimToLength(int length) {
    if (this.capacity > length && length >= 0) {
      for (int i = length; i < this.length; i++) {
        array[i] = null;
      }
      this.capacity = length;
    } else {
      throw new IllegalArgumentException(
          "The given capacity needs to be smaller than the current one!");
    }
  }

  /**
   * This methods deletes the whole data in the dynamic array.
   */
  public void clear() {
    while (isEmpty() == true) {
      remove();
    }
    capacity = 0;
  }

  /**
   * This method returns the index of an object in the dynamic array. If this object doesnt exist
   * the method returns the value -1.
   * 
   * @param object: object searched for
   * @return: index or if object not found -1
   */
  public int indexOf(T object) {
    for (int i = 0; i < capacity; i++) {
      if (array[i].equals(object)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * This methods checks whether an object is in the dynamic array or not.
   * 
   * @param object: object searched for
   * @return: true or false based on the existance of the object
   */
  public boolean contains(T object) {
    for (int i = 0; i < capacity; i++) {
      if (array[i].equals(object)) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method returns an iterator for the dynamic array. Iterating over the dynamic array with an
   * iterator isnt as fast as iterating with a for-loop over it, but the iteration with the iterator
   * is easier and more code efficient.
   */
  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      int index = 0;

      @Override
      public boolean hasNext() {
        if (index < capacity) {
          return true;
        }
        return false;
      }

      @Override
      public T next() {
        return array[index++];
      }
    };
  }

  // -------------------------private_methods-------------------------

  @SuppressWarnings("unchecked")
  private void expandDynamicArray() {
    if (length == 0) {
      length = 1;
    }
    T[] temp = (T[]) new Object[length * 2];
    length = length * 2;
    for (int i = 0; i < capacity; i++) {
      temp[i] = array[i];
    }
    array = temp;
  }
}
