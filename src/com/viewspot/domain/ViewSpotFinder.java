package com.viewspot.domain;

import java.util.*;
import java.util.stream.Collectors;

public class ViewSpotFinder {

    /**
     * Elements being considered for view spot search
     */
    List<Element> elements;

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    /**
     * Returns view spot elements that were found amongst all mesh elements
     *
     * @return view spot elements that were found
     */
    public List<Element> getViewSpotsFromElements() {
        // Getting all element ids to stop search once all have been considered
        List<Integer> allIds = elements.stream().map(Element::getId).collect(Collectors.toList());
        List<Integer> processedIds = new ArrayList<>();
        List<Element> viewSpots = new ArrayList<>();

        // Search for view spots until processed ids include all ids of elements
        do {
            // Searching for a local view spot from an element that was not yet considered
            List<Integer> remainingIds = allIds.stream()
                    .filter(i -> !processedIds.contains(i))
                    .collect(Collectors.toList());

            Optional<Element> startingElement = elements.stream()
                    .filter(e -> e.getId().equals(remainingIds.get(0)))
                    .findAny();

            // Searching for a local view spot from an element that was not yet considered
            SearchSummary search = null;
            if(startingElement.isPresent())
                search = this.searchLocalViewSpot(startingElement.get());
            else
                return viewSpots;

            // Updating viewSpots with element found and processedIds

            Element elementFound = search.getElementFound();
            if(!viewSpots.contains(elementFound))
                viewSpots.add(elementFound);
            processedIds.addAll(search.getProcessedIds());

        } while (!new HashSet<>(processedIds).equals(new HashSet<>(allIds)));


        return viewSpots;
    }

    /**
     * Returns a search summary consisting of a local view spot and elements ids that were considered for the search
     *
     * @param  startingElement for the local search
     * @return search summary
     */
    public SearchSummary searchLocalViewSpot(Element startingElement) {
        // Initializing fiirst element as starting point for search
        Element candidateViewSpot = startingElement;
        // Used to mark a peak or plateau
        boolean isPeakOrPlateau = false;
        // Processed elements ids to track elements considered for view spot
        List<Integer> processedIds = new ArrayList<>();

        // Continue search until we find a local view spot and no neighboring elements are higher
        do {
            // Generate neighbors to be considered
            List<Element> neighbors = this.getNeighbors(candidateViewSpot);

            // Update processed elements
            List<Integer> newIds = neighbors.stream()
                    .map(Element::getId)
                    .filter(i -> !processedIds.contains(i)).collect(Collectors.toList());

            if(!newIds.isEmpty())
                processedIds.addAll(newIds);

            // Get the highest element
            Element bestNextElement = Collections.max(neighbors);

            // Check if we hit peak or plateau otherwise update candidateViewSpot and continue
            if (this.atPeakOrPlateau(candidateViewSpot, bestNextElement))
                isPeakOrPlateau = true;
            else
                candidateViewSpot = bestNextElement;

        } while (!isPeakOrPlateau);

        return new SearchSummary(candidateViewSpot, processedIds);
    }

    /**
     * Returns all neighbors of an element, sharing at least one node id
     *
     * @param  element  element considered
     * @return list of neighbors found
     */
    private List<Element> getNeighbors(Element element) {
        return elements.stream()
                .filter(e -> e.getNodes().stream().anyMatch(n -> element.getNodes().contains(n)))
                .collect(Collectors.toList());
    }

    /**
     * Returns true if a new candidate view spot is no higher than current view spot, plateau is reached
     *
     * @param  element  current view spot candidate
     * @param  newElement next view spot candidate
     * @return true if next view spot candidate is no higher than current view spot
     */
    public Boolean atPeakOrPlateau(Element element, Element newElement) {
        return element.getValue() >= newElement.getValue();
    }
}
