package com.wellsfergo.bdd.service.Utils;

import com.google.gson.Gson;
import com.wellsfergo.bdd.service.glossary.BDDGlossary;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.slf4j.LoggerFactory.getLogger;

public class ResourceFileUtils {

  private static final Logger LOG = getLogger(ResourceFileUtils.class);

  private ResourceFileUtils() {}

  /**
   * Get resource files for a given path
   *
   * @param path the path to look for resources
   * @return a list of paths to files
   */
  ;

  public static List<String> getResourceFilesForDirectory(String path) {
    List<String> filenames = new ArrayList<>();
    InputStream in = getResourceAsStream(path);
    if (in == null) {
      LOG.error("The specified directory {} does not exists", path);
      return filenames;
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(in, UTF_8));
    filenames = br.lines().map(file -> path + "/" + file).collect(Collectors.toList());
    return filenames;
  }

  /**
   * Get the resource based on the given path, and return the file contents as a string.
   *
   * @param pathToResource the path to the given file
   * @return the string contents of the file
   */
  public static String fileContentsToString(String pathToResource) {
    InputStream inputStream =
        BDDGlossary.class.getClassLoader().getResourceAsStream(pathToResource);
    String jsonString = StringUtils.EMPTY;
    if (inputStream == null) {
      return jsonString;
    }
    try {
      jsonString = IOUtils.toString(inputStream, UTF_8);
    } catch (IOException e) {
      LOG.warn("Unable to parse the json file", e);
    }
    return jsonString;
  }

  /**
   * Get the resource based on the given path, and return the file contents as a map
   *
   * @param jsonFilePath the path to the json file
   * @return a map of the json file
   */
  public static Map<String, String> convertJsonFileToMap(String jsonFilePath) {
    Map<String, String> newMap = new LinkedHashMap<>();
    String jsonString = fileContentsToString(jsonFilePath);
    if (StringUtils.isNotBlank(jsonString)) {
      newMap = new Gson().fromJson(jsonString, Map.class);
    }
    return newMap;
  }

  private static InputStream getResourceAsStream(String resource) {

    return getContextClassLoader().getResourceAsStream(resource);
  }

  private static ClassLoader getContextClassLoader() {
    return currentThread().getContextClassLoader();
  }
}
