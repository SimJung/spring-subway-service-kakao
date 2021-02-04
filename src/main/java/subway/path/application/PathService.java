package subway.path.application;

import org.springframework.stereotype.Service;
import subway.line.dao.LineDao;
import subway.line.domain.Line;
import subway.path.domain.*;
import subway.path.dto.Fare;
import subway.path.dto.PathResponse;
import subway.path.dto.PathResult;
import subway.station.dao.StationDao;

import java.util.List;

@Service
public class PathService {
    LineDao lineDao;
    StationDao stationDao;
    Path path;

    public PathService(LineDao lineDao, StationDao stationDao, Path path){
        this.lineDao = lineDao;
        this.stationDao = stationDao;
        this.path = path;
    }

    public PathResponse findShortestPath(Long sourceId, Long targetId, Integer age) {
        List<Line> lines = lineDao.findAll();
        path.initPath(lines);
        PathResult result = path.findShortestPath(stationDao.findById(sourceId), stationDao.findById(targetId));
        Fare fare = new FareCalculator().calculate(result, lines, age);

        return new PathResponse(result, fare);

    }
}

