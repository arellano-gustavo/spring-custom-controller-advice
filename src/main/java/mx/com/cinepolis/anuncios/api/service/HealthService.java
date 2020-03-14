package mx.com.cinepolis.anuncios.api.service;

import java.util.List;
import java.util.Map;

public interface HealthService {
    Map<String, String> getInfo(String data) throws Exception;
    List<String> getLog(int last);
}
