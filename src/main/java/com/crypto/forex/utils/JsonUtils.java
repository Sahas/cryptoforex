package com.crypto.forex.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
  public static <T> T getObjectFromJsonNode(final JsonNode node, final Class<T> objectType)
      throws JsonProcessingException {
    final ObjectMapper mapper = new ObjectMapper();
    return mapper.treeToValue(node, objectType);
  }
}
