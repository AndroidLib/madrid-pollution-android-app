package repository;

import java.util.List;

import entities.PollutionStation;

public interface IStationsRepository {


    void setDelegate(IStationsRepositoryDelegate delegate);

    List<PollutionStation> getEmptyMadridCityPollutionStations();

    void getPollutionStationsAsync();

}
