package repository;

import java.util.Map;

import entities.PollutantInfo;

public interface IPollutantsRepository {

    Map<String, PollutantInfo> getPollutantInfoMap();
}
