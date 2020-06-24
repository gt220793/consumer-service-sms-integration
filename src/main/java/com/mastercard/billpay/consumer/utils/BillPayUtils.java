package com.mastercard.billpay.consumer.utils;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BillPayUtils {

  private BillPayUtils() {}

  private static final CsvMapper mapper = new CsvMapper();

  /**
   * Helper method to build CFI response.
   *
   * @param data response payload.
   * @param message failure cause if any.
   * @param messageCode flag denoting status of the operation.
   */
  public static <T> BillPayApiResponse<T> buildResponse(
      final List<T> data, final String message, final int messageCode) {
    return new BillPayApiResponse<>(messageCode, message, data);
  }

  /**
   * @param clazz
   * @param stream
   * @param <T>
   * @return
   * @throws IOException
   */
  public static <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
    CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
    ObjectReader reader = mapper.readerFor(clazz).with(schema);
    return reader.<T>readValues(stream).readAll();
  }
}
