package sqlParser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.AddAliasesVisitor;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlParserTest {

    public static void main(String[] args) throws JSQLParserException, IOException {
        Statement stmt = CCJSqlParserUtil.parse("select id, code, concat('抵扣金额', round(quota / 100, 2), '元，', if(base = 0, '无门槛', concat('满', round(base / 100, 2), '元可用'))) as rights_desc, base, quota, rights_type " +
                "    from base_rights where rights_type = '1' and platform = 'BB' " +
                "union all " +
                "select id, code, concat(round(quota / 100, 2), '%折扣，', if(base = 0, '无门槛，', concat('满', round(base / 100, 2), '元可用，')), if(has_top = 1, concat('折扣上限', round(top / 100, 2), '元'), '折扣无上限')) as rights_desc, base, quota, rights_type " +
                "    from base_rights where rights_type = '2' and platform = 'BB' " +
                "union all " +
                "select id, code, concat('储值金额', round((base + quota) / 100, 2), '元（本金', round(base / 100, 2), '元，馈赠金', round(quota / 100, 2), '元）' ) as rights_desc, base, quota, rights_type " +
                "    from base_rights where rights_type = '3' and platform = 'BB' " +
                "union all " +
                "(select br.id as id, min(br.code) as code, group_concat(rbi.name) as rights_desc, min(br.base) as base, (br.quota) as quota, min(br.rights_type) as rights_type " +
                "    from base_rights br " +
                "    left join rights_bybo_items rbi on rbi.right_id = br.id " +
                "    where br.rights_type = '4' and br.platform = 'BB' " +
                "    group by br.id);");

        Select selectStatement = (Select) stmt;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(selectStatement);



        Select stmt2 = (Select) CCJSqlParserUtil.parse("with " +
                "cte1 as " +
                "( " +
                "    select * from table1 where name like 'abc%' " +
                "), " +
                "cte2 as " +
                "( " +
                "    select * from table2 where id > 20 " +
                "), " +
                "cte3 as " +
                "( " +
                "    select * from table3 where price < 100 " +
                ") " +
                "select a.* from cte1 a, cte2 b, cte3 c where a.id = b.id and a.id = c.id");

        // schema 的 select
        Select stmt3 = (Select) CCJSqlParserUtil.parse("SELECT t.* " +
                "                  FROM file.file_os_list t " +
                "                  LIMIT 501 ");
        TablesNamesFinder tablesNamesFinder2 = new TablesNamesFinder();
        List<String> tableList2 = tablesNamesFinder2.getTableList(stmt3);


        final AddAliasesVisitor instance = new AddAliasesVisitor();
        selectStatement.getSelectBody().accept(instance);

        SelectUtils.addExpression(stmt2, new Column("col4"));

        Map<String, Expression> map = new HashMap<>();
        Map<String, Expression> unionMap = new HashMap<>();
        for (SelectItem selectItem : (( PlainSelect)stmt2.getSelectBody()).getSelectItems()) {
            selectItem.accept(new SelectItemVisitorAdapter() {
                @Override
                public void visit(SelectExpressionItem item) {
                    if (item.getAlias() == null) {
                        map.put(item.getExpression().toString(), item.getExpression());
                    } else {
                        map.put(item.getAlias().getName(), item.getExpression());
                    }

                }
            });
        }

//        for (SelectItem selectItem : (( PlainSelect)selectStatement.getSelectBody()).getSelectItems()) {
//            selectItem.accept(new SelectItemVisitorAdapter() {
//                @Override
//                public void visit(SelectExpressionItem item) {
//                    unionMap.put(item.getAlias().getName(), item.getExpression());
//                }
//            });
//        }

        System.out.println("map " + map);

        Writer writer = new OutputStreamWriter(System.out);
        writer.write(tableList.toString());
    }
}
