package test.domain;

import com.viewspot.domain.Element;
import com.viewspot.domain.SearchSummary;
import com.viewspot.domain.ViewSpotFinder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ViewSpotFinderTest {

    @Test
    public void searchLocalViewSpot() {
        Element element1 = new Element(0, Arrays.asList(0, 1, 2), 0.5);
        Element element2 = new Element(1, Arrays.asList(2, 3, 4), 0.8);
        Element element3 = new Element(2, Arrays.asList(4, 5, 6), 0.9);
        List<Element> testElements = Stream.of(element1, element2, element3)
                .collect(Collectors.toList());
        ViewSpotFinder viewSpotFinder = new ViewSpotFinder();
        viewSpotFinder.setElements(testElements);
        SearchSummary summary = viewSpotFinder.searchLocalViewSpot(testElements.get(0));

        Assert.assertEquals(summary, new SearchSummary(element3, Arrays.asList(0, 1, 2)));
    }

    @Test
    public void getViewSpotsFromElements() {
        Element element1 = new Element(0, Arrays.asList(0, 1, 2), 0.5);
        Element element2 = new Element(1, Arrays.asList(2, 3, 4), 0.8);
        Element element3 = new Element(2, Arrays.asList(4, 5, 6), 0.9);
        Element element4 = new Element(3, Arrays.asList(6, 7, 8), 0.7);
        Element element5 = new Element(4, Arrays.asList(8, 9, 10), 0.5);
        Element element6 = new Element(5, Arrays.asList(10, 11, 12), 0.3);
        Element element7 = new Element(6, Arrays.asList(12, 13, 14), 0.6);
        Element element8 = new Element(7, Arrays.asList(14, 15, 16), 0.9);
        Element element9 = new Element(8, Arrays.asList(16, 17, 18), 1.0);
        List<Element> testElements = Stream.of(element1, element2, element3, element4, element5, element6, element7, element8, element9)
                .collect(Collectors.toList());
        ViewSpotFinder viewSpotFinder = new ViewSpotFinder();
        viewSpotFinder.setElements(testElements);
        List<Element> viewSpots = viewSpotFinder.getViewSpotsFromElements();

        Assert.assertEquals(viewSpots, Stream.of(element3, element9).collect(Collectors.toList()));
    }
}
