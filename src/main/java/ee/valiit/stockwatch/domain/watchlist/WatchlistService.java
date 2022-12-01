package ee.valiit.stockwatch.domain.watchlist;

import ee.valiit.stockwatch.business.watchlist.WatchlistRequest;
import ee.valiit.stockwatch.domain.instrument.instrument.Instrument;
import ee.valiit.stockwatch.domain.instrument.instrument.InstrumentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

@Service
public class WatchlistService {

    @Resource
    private WatchlistMapper watchlistMapper;

    @Resource
    private WatchlistRepository watchlistRepository;

    @Resource
    private InstrumentService instrumentService;


    public void addInstrumentToWatchlist(WatchlistRequest request) {
        instrumentService.addNewInstrument(request.getTicker());
        Instrument instrument = instrumentService.findInstrumentByTicker(request.getTicker());

        Watchlist watchlist = watchlistMapper.watchlistRequestToWatchlist(request);

        watchlist.setInstrument(instrument);
        watchlist.setAdditionDate(LocalDate.now());

        watchlistRepository.save(watchlist);
    }
}
