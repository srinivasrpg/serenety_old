package com.wellsfergo.bdd.service.glossary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wellsfergo.bdd.service.Utils.ResourceFileUtils.fileContentsToString;
import static com.wellsfergo.bdd.service.Utils.ResourceFileUtils.getResourceFilesForDirectory;
import static org.slf4j.LoggerFactory.getLogger;

@Slf4j
public class BDDGlossary {
  private static final Type HASH_MAP_TYPE = new TypeToken<HashMap<String, Object>>() {}.getType();

  private static final Logger LOG = getLogger(BDDGlossary.class);
  // Path to the glossary files
  private static final String SOURCE_PATH = "glossary";

  private static final Map<String, Object> terms = new HashMap<>();

  // Initialize terms map before running tests in threads.

  static {
    buildTerms();
  }

  /**
   * Get the value from the glossary by the term(key)
   *
   * @param term the key in the glossary
   * @return the key's value in the glossary,or the key if does not match any in the glossary
   */
  public static String getGlossaryTerm(String term) {
    Object object = terms.get(term);
    if (object instanceof String) {
      return (String) object;
    }
    LOG.warn("Glossary term entry '{}' is not found.", term);
    return term;
  }

  private static void buildTerms() {
    List<String> resourceFiles = getResourceFilesForDirectory(SOURCE_PATH);
    resourceFiles.removeIf(x -> !x.endsWith(".json"));
    resourceFiles.forEach(item -> terms.putAll(convertJsonFileToMap(item)));
  }

  private static Map<String, Object> convertJsonFileToMap(String glossaryPath) {
    Map<String, Object> newMap = new HashMap<>();
    String jsonString = fileContentsToString(glossaryPath);
    if (StringUtils.isNotBlank(jsonString)) {
      newMap = new Gson().fromJson(jsonString, HASH_MAP_TYPE);
    }
    return newMap;
  }
}
