package pl.sonmiike.parliamentclient.apiclient;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sonmiike.parliamentclient.contract.ParliamentClubDTO;
import pl.sonmiike.parliamentclient.contract.ParliamentMemberDTO;
import pl.sonmiike.parliamentclient.contract.ParliamentVotingsDTO;
import pl.sonmiike.parliamentclient.contract.VotesDTO;
import pl.sonmiike.parliamentclient.contract.side.ParliamentSessionDTO;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AskApiClient implements IAskApiClient {

    private final RestTemplate restTemplate = new RestTemplate();


    private static final List<LocalDate> parliamentTerm10Dates = new ArrayList<>();

    @Value("${api.url}")
    private String apiUrl;

    public List<ParliamentClubDTO> getClubs() {
        URI url = buildUri("sejm", "term10", "clubs");
        ParliamentClubDTO[] clubsArray = restTemplate.getForObject(url, ParliamentClubDTO[].class);
        return Arrays.asList(clubsArray);
    }

    public List<ParliamentMemberDTO> getMPs() {
        URI url = buildUri("sejm", "term10", "MP");
        ParliamentMemberDTO[] mpsArray = restTemplate.getForObject(url, ParliamentMemberDTO[].class);
        return Arrays.asList(mpsArray);
    }

    public List<ParliamentVotingsDTO> getVotings() {
        List<CompletableFuture<ParliamentVotingsDTO[]>> futures = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            URI url = buildUri("sejm", "term10", "votings", String.valueOf(i));
            CompletableFuture<ParliamentVotingsDTO[]> future = CompletableFuture.supplyAsync(() ->
                    restTemplate.getForObject(url, ParliamentVotingsDTO[].class));
            futures.add(future);
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .flatMap(Arrays::stream)
                        .collect(Collectors.toList()))
                .join();
    }

    public List<VotesDTO> getMPVotings(long mpId) {
        List<CompletableFuture<VotesDTO[]>> futures = new ArrayList<>();
        getParliamentTerm10Dates();

        for (int i = 1; i < 6; i++) {
            for (LocalDate date : parliamentTerm10Dates) {
                URI url = buildUri("sejm", "term10", "MP", String.valueOf(mpId), "votings", String.valueOf(i), String.valueOf(date));
                CompletableFuture<VotesDTO[]> future = CompletableFuture.supplyAsync(() ->
                        restTemplate.getForObject(url, VotesDTO[].class));
                futures.add(future);
            }
        }

        List<VotesDTO> votesDTOList = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .flatMap(Arrays::stream)
                        .filter(vote -> !vote.getVote().equalsIgnoreCase("absent"))
                        .collect(Collectors.toList()))
                .join();

        votesDTOList.sort(Comparator.comparing(VotesDTO::getDate));
        votesDTOList.forEach(vote -> vote.setMpID(mpId));

        return votesDTOList.isEmpty() ? null : votesDTOList;
    }



    public void getParliamentTerm10Dates() {
        if (!parliamentTerm10Dates.isEmpty()) {
            return;
        }

        URI uri = UriComponentsBuilder.fromUri(buildUri("sejm", "term10", "videos"))
                .queryParam("type", "posiedzenie")
                .queryParam("till", LocalDate.now().plusDays(1))
                .build()
                .toUri();

        ParliamentSessionDTO[] parliamentSessionDTOList = restTemplate.getForObject(uri, ParliamentSessionDTO[].class);
        parliamentTerm10Dates.addAll(Arrays.asList(parliamentSessionDTOList).stream().map(ParliamentSessionDTO::getStartDateTime).map(LocalDate::from).toList());
    }

    private URI buildUri(String... pathSegments) {
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(apiUrl)
                .pathSegment(pathSegments)
                .build()
                .toUri();
    }
}
