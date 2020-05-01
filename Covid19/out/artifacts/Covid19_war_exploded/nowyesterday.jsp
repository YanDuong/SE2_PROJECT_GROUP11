<%--
  Created by IntelliJ IDEA.
  User: khangtu
  Date: 4/9/20
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
</head>

<body>

    <ul class="nav nav-pills mt_buttons" role="tablist">
        <li class="mt_pills active" data-toggle="tab" role="tab" aria-controls="nav-today" id="nav-today-tab"><a href="#nav-today">Now</a></li>
        <li class="mt_pills" data-toggle="tab" role="tab" aria-controls="nav-yesterday" id="nav-yesterday-tab"><a href="#nav-yesterday">Yesterday</a></li>
    </ul>


    <div id="main_table_countries_today_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
        <div id="ctabs-row" class="row">
            <div id="continents-tabtoday" class="w-100">
                <nav id="ctabstoday" class="ctabs" style="">
                    <ul class="nav nav-tabs" id="continents_buttons">
                        <li class="mt_pills mt_pills_c active" id="nav-all-tab" data-toggle="pill" href="#nav-all" role="tab" aria-controls="nav-all" aria-selected="true"><a href="#c-all&quot;">All</a></li>
                        <li class="mt_pills mt_pills_c" id="nav-europe-tab" data-toggle="pill" href="#nav-europe" role="tab" aria-controls="nav-all" aria-selected="true"><a href="#c-europe&quot;">Europe</a></li>
                        <li class="mt_pills mt_pills_c" id="nav-na-tab" data-toggle="pill" href="#nav-na" role="tab" aria-controls="nav-all" aria-selected="true"><a href="#c-north-america&quot;">North America</a></li>
                        <li class="mt_pills mt_pills_c" id="nav-asia-tab" data-toggle="pill" href="#nav-asia" role="tab" aria-controls="nav-all" aria-selected="true"><a href="#c-asia&quot;">Asia</a></li>
                        <li class="mt_pills mt_pills_c" id="nav-sa-tab" data-toggle="pill" href="#nav-sa" role="tab" aria-controls="nav-all" aria-selected="true"><a href="#c-south-america&quot;">South America</a></li>
                        <li class="mt_pills mt_pills_c" id="nav-africa-tab" data-toggle="pill" href="#nav-africa" role="tab" aria-controls="nav-all" aria-selected="true"><a href="#c-africa&quot;">Africa</a></li>
                        <li class="mt_pills mt_pills_c" id="nav-oceania-tab" data-toggle="pill" href="#nav-oceania" role="tab" aria-controls="nav-all" aria-selected="true"><a href="#c-oceania&quot;">Oceania</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>



    <script>
        var tabs = ["today", "yesterday"];
        var cur_continent = 'All';
        var cur_table = 'main_table_countries_today';
        $(document).ready(function() {

            // $.fn.dataTable.moment('MMM DD');
            tabs.forEach(function(val) {


                $('#nav-' + val + '-tab a').click(function(e) {
                    e.preventDefault();
                    $(this).tab('show');
                })

                /*       var trfirst = $('#main_table_countries_' + val).find('tr:last').clone();

                trfirst.find('td:first').css('text-align', 'left');
                trfirst.find('td:first').html('World');

                //trfirst.find('td:last').html('Jan 10');
                trfirst.find('td:last').css('font-size', '13px');


                // light grey background
                trfirst.css('background-color', '#DFDFDF');
                trfirst.find('td').css('background-color', '#DFDFDF');
                trfirst.find('td').css('color', '#363945');
                //            trfirst.find('td').css('font-size', '13px');
                trfirst.find('td').css('font-weight', 'normal');

                //trfirst.find('td:first').css('font-weight', 'bold');


                $('#main_table_countries_' + val).prepend(trfirst);
                */

                $('#main_table_countries_' + val).dataTable({

                    "scrollCollapse": true,
                    "scrollX": true,
                    "order": [

                        [1, 'desc']
                    ],
                    //"dom": '<"top"f><"clear">',
                    "dom": "<'row pills_row'<'col-sm-9 col-md-9'><'col-sm-3 col-md-3'f>>" +
                        "<'#ctabs-row.row'<'#continents-tab" + val + ".w-100'>>",
                    //"<'row'<tr>>",

                    "paging": false,

                    initComplete: function(settings, json) {

                        var ctabs = $('#ctabs').clone();

                        ctabs.attr('id', 'ctabs' + val);
                        ctabs.appendTo($('#continents-tab' + val));

                        ctabs.find('a').on('click', function() {

                            cur_continent = $(this).html();
                            var cur_continent_original = cur_continent;
                            if (cur_continent == 'Oceania') {

                                cur_continent = 'Australia/Oceania';
                            }
                            cur_table = 'main_table_countries_' + val;
                            if (val == 'today') {

                                // trigger yesterday

                                $('#ctabsyesterday li').removeClass('active');
                                $('#ctabsyesterday').find('a:contains("' + cur_continent_original + '")').parent().addClass('active');

                                $('#main_table_countries_yesterday').find('.row_continent').hide();
                                $('#main_table_countries_yesterday').DataTable().draw();
                                $('#main_table_countries_yesterday').find('tr[data-continent="' + cur_continent + '"]').show();
                            } else {

                                // trigger today
                                $('#ctabstoday li').removeClass('active');
                                $('#ctabstoday').find('a:contains("' + cur_continent_original + '")').parent().addClass('active');

                                $('#main_table_countries_today').find('.row_continent').hide();
                                $('#main_table_countries_today').DataTable().draw();
                                $('#main_table_countries_today').find('tr[data-continent="' + cur_continent + '"]').show();

                            }

                            $('#main_table_countries_' + val).find('.row_continent').hide();


                            $('#main_table_countries_' + val).DataTable().draw();
                            $('#main_table_countries_' + val).find('tr[data-continent="' + cur_continent + '"]').show();


                        })

                        ctabs.show();

                        $('#main_table_countries_' + val).DataTable().draw();


                    }

                });


                $.fn.dataTable.ext.search.push(

                    function(settings, data, dataIndex) {

                        // Don't filter on anything other than "myTable"
                        /*   if (settings.nTable.id !== cur_table) {
                        return true;
                        }*/

                        // Filtering for "myTable".
                        if (cur_continent == 'All') {
                            return true;
                        }

                        if (
                            data[12] == cur_continent
                        ) {

                            return true;
                        } else {
                            return false;
                        }
                    }
                );
            });

        });

    </script>



    </div>
    </div>

</body>

</html>
