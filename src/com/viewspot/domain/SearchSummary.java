package com.viewspot.domain;

import java.util.List;
import java.util.Objects;

/**
 * Search summary returned after completing a local view spot search
 */
public class SearchSummary {

    /**
     * Local view spot found
     */
    private Element elementFound;
    /**
     * Element ids for elements considered for local view spot
     */
    private List<Integer> processedIds;

    public SearchSummary(Element elementFound, List<Integer> processedIds) {
        this.elementFound = elementFound;
        this.processedIds = processedIds;
    }

    public Element getElementFound() {
        return elementFound;
    }

    public void setElementFound(Element elementFound) {
        this.elementFound = elementFound;
    }

    public List<Integer> getProcessedIds() {
        return processedIds;
    }

    public void setProcessedIds(List<Integer> processedIds) {
        this.processedIds = processedIds;
    }

    @Override
    public String toString() {
        return "SearchSummary{" +
                "elementFound=" + elementFound +
                ", processedIds=" + processedIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchSummary)) return false;
        SearchSummary that = (SearchSummary) o;
        return getElementFound().equals(that.getElementFound()) &&
                getProcessedIds().equals(that.getProcessedIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getElementFound(), getProcessedIds());
    }
}
