package com.exadatum.product;

import static us.fatehi.commandlineparser.CommandLineUtility.applyApplicationLogLevel;
import static us.fatehi.commandlineparser.CommandLineUtility.logSystemProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.sql.DataSource;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schema.View;
import schemacrawler.schemacrawler.DatabaseConnectionOptions;
import schemacrawler.schemacrawler.ExcludeAll;
import schemacrawler.schemacrawler.RegularExpressionInclusionRule;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.utility.SchemaCrawlerUtility;

public final class SchemaCrawler{

    public static void main(final String[] args) throws Exception {
        // Set logging on
        //applyApplicationLogLevel(Level.ALL);
        // Log system properties and classpath
        //logSystemProperties();
        // Create the options
        final SchemaCrawlerOptions options = new SchemaCrawlerOptions();
        // Set what details are required in the schema - this affects the
        // time taken to crawl the schema
        options.setSchemaInfoLevel(SchemaInfoLevelBuilder.standard());
        options.setRoutineInclusionRule(new ExcludeAll());
        //options.setSchemaInclusionRule(new RegularExpressionInclusionRule("PUBLIC.BOOKS"));
        // Get the schema definition
        final Catalog catalog = SchemaCrawlerUtility.getCatalog(getConnection(),options);
    for (final Schema schema: catalog.getSchemas()) {
      System.out.println("Database: "+schema);
      for (Table table: catalog.getTables(schema)) {
        System.out.print("Tables:  " + table);
        if (table instanceof View) {
          System.out.println(" (VIEW)");
        }
        else {
          System.out.println();
        }

        for (Column column: table.getColumns()) {
          System.out.println("Columns:  " + column + " ("
                             + column.getColumnDataType() + ")");
        }
      }
    }

    }

    private static Connection getConnection()
            throws SchemaCrawlerException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        final DataSource dataSource = new DatabaseConnectionOptions("jdbc:mysql://127.0.0.1:3306/");
        return dataSource.getConnection("jksingh", "jassikaran");
    }

}
