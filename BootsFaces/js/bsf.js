/*!
 * Copyright 2014 Riccardo Massera (TheCoder4.Eu)
 * BootsFaces JS
 * author: TheCoder4.eu
 */
;BsF={};BsF.ajax={};BsF.callback={};BsF.isFunction=function(a){var b={};return a&&b.toString.call(a)==="[object Function]"};BsF.ajax.onevent=function(b){if(b.status=="success"){var c=b.source.id.replace(/[^a-zA-Z0-9]+/g,"_");var a=BsF.callback[c];a()}};BsF.ajax.cb=function(i,h,c,d){var g=arguments.length;var b=i.id;var j=b.replace(/[^a-zA-Z0-9]+/g,"_");var a={};a.execute="@all";a[b]=b;if(g==4){BsF.callback[j]=d;a.render=c;a.onevent=BsF.ajax.onevent}if(g==3){if(BsF.isFunction(c)){BsF.callback[j]=c;a.onevent=BsF.ajax.onevent}else{a.render=c}}jsf.ajax.request(i,h,a);return false};BsF.ajax.paginate=function(g,f,a,h,d){var b={};b.execute="@this";b.render=d;b[h]=a;jsf.ajax.request(h,f,b);return false};if($.datepicker){var generateHTML_orig=$.datepicker._generateHTML;$.datepicker._generateHTML=function(){var a=generateHTML_orig.apply(this,arguments);a=a.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-w'\s*>[^<]+<\/span>/,'<span class="glyphicon glyphicon-chevron-left"></span>');a=a.replace(/<span\s+class='ui-icon\s+ui-icon-circle-triangle-e'\s*>[^<]+<\/span>/,'<span class="glyphicon glyphicon-chevron-right"></span>');return a}};