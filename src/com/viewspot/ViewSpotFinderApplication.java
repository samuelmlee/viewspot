package com.viewspot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewspot.domain.Element;
import com.viewspot.domain.MeshData;
import com.viewspot.domain.Value;
import com.viewspot.domain.ViewSpotFinder;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ViewSpotFinderApplication {

    public static void main(String[] args) throws IOException {
        String filePath = "";
        int viewSpotsGoal = 0;
        // Setting filePath and viewSpotsGoal from args
        if(args.length == 2) {
            filePath = args[0];
            viewSpotsGoal = Integer.parseInt(args[1]);
        }

        // Deserialize json
        ObjectMapper mapper = new ObjectMapper();
        MeshData meshData = mapper.readValue(new File(filePath), MeshData.class);

        // Getting elements and values and updating elements
        List<Element> meshElements = meshData.getElements();
        List<Value> meshValues = meshData.getValues();
        meshElements.forEach(e -> e.updateValueFromData(meshValues));

        // Calling getViewSpotsFromElements from mesh elements
        ViewSpotFinder viewSpotFinder = new ViewSpotFinder();
        viewSpotFinder.setElements(meshElements);
        List<Element> viewSpotsFound = viewSpotFinder.getViewSpotsFromElements();

        // Limiting search results and sort
        List<Element> viewSpotsRequested = viewSpotsFound
                .stream()
                .limit(viewSpotsGoal)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        System.out.println(viewSpotsRequested.toString());
    }
}
