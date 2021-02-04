package subway.path.domain;

import subway.line.domain.Line;
import subway.path.dto.Fare;
import subway.station.domain.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LineExtraFarePolicy {

    public int apply(PathVertices pathVertices, List<Line> lineList){
        return getExtraFareList(pathVertices, lineList).stream().max(Integer::compare).orElse(0);
    }

    public List<Integer> getExtraFareList(PathVertices pathVertices, List<Line> lines) {
        List<Line> passingLine = new ArrayList<>();
        Station prevStation = null;
        Station nextStation = null;
        for(PathVertex pathVertex : pathVertices.getPathVertexList()){
            prevStation = nextStation;
            nextStation = pathVertex.getStation();
            if(prevStation == null) continue;

            passingLine.add(getLineBySection(lines, prevStation, nextStation));
        }
        return passingLine.stream()
                .filter(Objects::nonNull)
                .map(Line::getExtraFare)
                .collect(Collectors.toList());
    }

    private Line getLineBySection(List<Line> lines, final Station prev, final Station next){
        return lines.stream()
                .filter(line -> line.hasSectionByStations(prev, next))
                .findFirst()
                .orElse(null);
    }
}
