package com.justeat.mickeydb.generator;

import com.google.common.base.Objects;
import com.justeat.mickeydb.ContentUriInfo;
import com.justeat.mickeydb.MickeyDatabaseModel;
import com.justeat.mickeydb.ModelUtil;
import com.justeat.mickeydb.Strings;
import com.justeat.mickeydb.generator.SqliteDatabaseSnapshot;
import com.justeat.mickeydb.mickeyLang.ActionStatement;
import com.justeat.mickeydb.mickeyLang.ColumnSource;
import com.justeat.mickeydb.mickeyLang.ColumnType;
import com.justeat.mickeydb.mickeyLang.ContentUri;
import com.justeat.mickeydb.mickeyLang.ContentUriParamSegment;
import com.justeat.mickeydb.mickeyLang.ContentUriSegment;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class CustomActionsGenerator {
  public CharSequence generate(final MickeyDatabaseModel model, final ContentUriInfo content) {
    StringConcatenation _builder = new StringConcatenation();
    SqliteDatabaseSnapshot snapshot = model.getSnapshot();
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Generated by Mickey DB");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("package ");
    String _packageName = model.getPackageName();
    _builder.append(_packageName, "");
    _builder.append(".actions;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import com.justeat.mickeydb.ActiveRecordFactory;");
    _builder.newLine();
    _builder.append("import android.content.ContentValues;");
    _builder.newLine();
    _builder.append("import android.database.sqlite.SQLiteDatabase;");
    _builder.newLine();
    _builder.append("import android.net.Uri;");
    _builder.newLine();
    _builder.append("import com.justeat.mickeydb.CustomActions;");
    _builder.newLine();
    _builder.append("import com.justeat.mickeydb.MickeyContentProvider;");
    _builder.newLine();
    _builder.append("import com.justeat.mickeydb.Query;");
    _builder.newLine();
    _builder.append("import ");
    String _packageName_1 = model.getPackageName();
    _builder.append(_packageName_1, "");
    _builder.append(".");
    String _databaseName = model.getDatabaseName();
    _builder.append(_databaseName, "");
    _builder.append("Contract.");
    String _type = content.getType();
    String _pascalize = Strings.pascalize(_type);
    _builder.append(_pascalize, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    String _packageName_2 = model.getPackageName();
    _builder.append(_packageName_2, "");
    _builder.append(".Abstract");
    String _databaseName_1 = model.getDatabaseName();
    _builder.append(_databaseName_1, "");
    _builder.append("OpenHelper.Sources;");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    String _packageName_3 = model.getPackageName();
    _builder.append(_packageName_3, "");
    _builder.append(".");
    String _type_1 = content.getType();
    String _pascalize_1 = Strings.pascalize(_type_1);
    _builder.append(_pascalize_1, "");
    _builder.append("Record;");
    _builder.newLineIfNotEmpty();
    _builder.append("import java.util.List;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    String _name = content.getName();
    String _pascalize_2 = Strings.pascalize(_name);
    _builder.append(_pascalize_2, "");
    _builder.append("Actions extends CustomActions {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void addQueryExpressionsFromUriSegmentParams(Uri uri, Query query) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("List<String> segments = uri.getPathSegments();");
    _builder.newLine();
    {
      ActionStatement _action = content.getAction();
      ContentUri _uri = _action.getUri();
      EList<ContentUriSegment> _segments = _uri.getSegments();
      Iterable<Pair<Integer, ContentUriSegment>> _indexed = IterableExtensions.<ContentUriSegment>indexed(_segments);
      for(final Pair<Integer, ContentUriSegment> entry : _indexed) {
        {
          ContentUriSegment _value = entry.getValue();
          if ((_value instanceof ContentUriParamSegment)) {
            _builder.append("\t\t");
            ContentUriSegment _value_1 = entry.getValue();
            ContentUriParamSegment param = ((ContentUriParamSegment) _value_1);
            _builder.newLineIfNotEmpty();
            {
              ColumnSource _param = param.getParam();
              ColumnType _inferredColumnType = ModelUtil.getInferredColumnType(_param);
              boolean _notEquals = (!Objects.equal(_inferredColumnType, ColumnType.TEXT));
              if (_notEquals) {
                _builder.append("\t\t");
                _builder.append("long ");
                ColumnSource _param_1 = param.getParam();
                String _name_1 = _param_1.getName();
                _builder.append(_name_1, "\t\t");
                _builder.append("Slug = Long.parseLong(segments.get(");
                Integer _key = entry.getKey();
                _builder.append(_key, "\t\t");
                _builder.append("));");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t\t");
                _builder.append("String ");
                ColumnSource _param_2 = param.getParam();
                String _name_2 = _param_2.getName();
                _builder.append(_name_2, "\t\t");
                _builder.append("Slug = segments.get(");
                Integer _key_1 = entry.getKey();
                _builder.append(_key_1, "\t\t");
                _builder.append(");");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    {
      ActionStatement _action_1 = content.getAction();
      ContentUri _uri_1 = _action_1.getUri();
      EList<ContentUriSegment> _segments_1 = _uri_1.getSegments();
      Iterable<Pair<Integer, ContentUriSegment>> _indexed_1 = IterableExtensions.<ContentUriSegment>indexed(_segments_1);
      for(final Pair<Integer, ContentUriSegment> entry_1 : _indexed_1) {
        {
          ContentUriSegment _value_2 = entry_1.getValue();
          if ((_value_2 instanceof ContentUriParamSegment)) {
            _builder.append("\t\t");
            ContentUriSegment _value_3 = entry_1.getValue();
            ContentUriParamSegment param_1 = ((ContentUriParamSegment) _value_3);
            _builder.newLineIfNotEmpty();
            {
              ColumnSource _param_3 = param_1.getParam();
              ColumnType _inferredColumnType_1 = ModelUtil.getInferredColumnType(_param_3);
              boolean _notEquals_1 = (!Objects.equal(_inferredColumnType_1, ColumnType.TEXT));
              if (_notEquals_1) {
                _builder.append("\t\t");
                _builder.append("query.expr(");
                String _type_2 = content.getType();
                String _pascalize_3 = Strings.pascalize(_type_2);
                _builder.append(_pascalize_3, "\t\t");
                _builder.append(".");
                ColumnSource _param_4 = param_1.getParam();
                String _name_3 = _param_4.getName();
                String _underscore = Strings.underscore(_name_3);
                String _upperCase = _underscore.toUpperCase();
                _builder.append(_upperCase, "\t\t");
                _builder.append(", Query.Op.EQ, ");
                ColumnSource _param_5 = param_1.getParam();
                String _name_4 = _param_5.getName();
                _builder.append(_name_4, "\t\t");
                _builder.append("Slug);");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t\t");
                _builder.append("query.expr(\"cast(\" + ");
                String _type_3 = content.getType();
                String _pascalize_4 = Strings.pascalize(_type_3);
                _builder.append(_pascalize_4, "\t\t");
                _builder.append(".");
                ColumnSource _param_6 = param_1.getParam();
                String _name_5 = _param_6.getName();
                String _underscore_1 = Strings.underscore(_name_5);
                String _upperCase_1 = _underscore_1.toUpperCase();
                _builder.append(_upperCase_1, "\t\t");
                _builder.append(" + \" as text)\", Query.Op.EQ, ");
                ColumnSource _param_7 = param_1.getParam();
                String _name_6 = _param_7.getName();
                _builder.append(_name_6, "\t\t");
                _builder.append("Slug);");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t\t");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String getSourceName() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return Sources.");
    String _type_4 = content.getType();
    String _underscore_2 = Strings.underscore(_type_4);
    String _upperCase_2 = _underscore_2.toUpperCase();
    _builder.append(_upperCase_2, "\t\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ActiveRecordFactory<?> getActiveRecordFactory() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    String _type_5 = content.getType();
    String _pascalize_5 = Strings.pascalize(_type_5);
    _builder.append(_pascalize_5, "\t\t");
    _builder.append("Record.getFactory();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
