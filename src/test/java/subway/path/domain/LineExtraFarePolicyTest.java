package subway.path.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.line.domain.Line;
import subway.line.domain.Section;
import subway.path.dto.Fare;
import subway.path.dto.PathResult;
import subway.station.domain.Station;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LineExtraFarePolicyTest {
    Line l1, l2;
    Station s1, s2;
    PathVertex vertex1, vertex2;
    PathVertices pathVertices;
    PathResult pathResult;
    FareCalculator fareCalculator;
    @BeforeEach
    void setUp(){
        s1 = new Station(1L, "s1");
        s2 = new Station(2L, "s2");
        l1 = new Line("l1", "c1", 200);
        l2 = new Line("l2", "c2", 1000);
        l1.addSection(new Section(s1, s2, 5));
        vertex1 = new PathVertex(s1);
        vertex2 = new PathVertex(s2);
        pathVertices = PathVertices.of(Arrays.asList(vertex1, vertex2));
        pathResult = new PathResult(pathVertices, 5);
        fareCalculator = new FareCalculator();
    }

    @DisplayName("포함된 노선 중, 가장 비싼 추가 요금 조회 테스트")
    @Test
    void extraFareTest(){
        int fare = new LineExtraFarePolicy().apply(pathVertices, Arrays.asList(l1, l2));
        assertThat(fare).isEqualTo(200);
    }
}
