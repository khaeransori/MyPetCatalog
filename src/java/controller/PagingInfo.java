/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;

/**
 *
 * @author Khaer Ansori
 */
public class PagingInfo implements Serializable{
    private int batchSize = 5;
    private int firstItem = 0;
    private int itemCount = -1;
    
    public int getBatchSize() {
        return batchSize;
    }
    
    public int getItemCount() {
        return itemCount;
    }
    
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    
    public int getFirstItem() {
        if(itemCount==-1) {
            throw new IllegalStateException("itemCount must be set before invoking getFistItem");
        }
        if(firstItem >= itemCount) {
            if(itemCount == 0) {
                firstItem = 0;
            } else {
                int zeroBasedItemCount = itemCount - 1;
                double pageDouble = zeroBasedItemCount / batchSize;
                int page = (int)Math.floor(pageDouble);
                firstItem = page * batchSize;
            }
        }
        return firstItem;
    }
    
    public void setFirstItem(int firstItem) {
        this.firstItem = firstItem;
    }
    
    public int getLastItem() {
        getFirstItem();
        return firstItem + batchSize > itemCount ? itemCount : firstItem + batchSize;
    }
    
    public void nextPage() {
        getFirstItem();
        if(firstItem + batchSize < itemCount) {
            firstItem += batchSize;
        }
    }
    
    public void previousPage() {
        getFirstItem();
        firstItem -= batchSize;
        if(firstItem < 0) {
            firstItem = 0;
        }
    }
    
    public boolean getIsNextItems() {
        if(getLastItem() < getItemCount()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean getIsPrevItems() {
        if(firstItem >= batchSize) {
            return true;
        } else {
            return false;
        }
    }
}
