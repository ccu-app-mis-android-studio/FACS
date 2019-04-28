var firebase;
$(function(){
  var $name = $('#name'),
      $content = $('#content'),
      $btn = $('#btn'),
      $show = $('#show'),
      $chatWindow = $('.chatWindow')
      ms = new Date().getTime();
  var config = { databaseURL: "https://chathtml.firebaseio.com/" }; //傳到自己的firebase網址
  firebase.initializeApp(config);
  var database = firebase.database().ref();
  //firebase.database().ref('chathtml/').remove(); //刪除chathtml底下的所有資料

  $btn.on('click',write);
  //設定在對話框按下 enter 的事件 ( enter 預設 keyCode 為 13 )
  $content.on('keydown', function(e){
    if(e.keyCode == 13){
      write();
    }
  });
  
  function write(){
    var date = new Date();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    if(hour < 10){
      hour = '0' + hour;
    }
    if(minute < 10){
      minute = '0' + minute;
    }
    if(second < 10){
      second = '0' + second;
    }
    var now = hour + ':' + minute + ':' + second;
    //記得一開始要先宣告 ms = new Date().getTime()
    var postData = {
      name: $('#name').val(),
      content: $('#content').val(),
      time: now,
      id: 'id' + ms
    };
    database.push(postData);
    $content.val('');
  }
  
  //第一次載入資料庫時顯示所有內容
  database.once('value', function(snapshot) {
    $chatWindow.html('');
    for(var i in snapshot.val()){
       $chatWindow.append(snapshot.val()[i].time + '&nbsp&nbsp' + snapshot.val()[i].name + ' 說: ' + snapshot.val()[i].content + "<BR>");
       
    }
  });

  //每一次資料庫有變動時，獲取最新一筆內容呈現
  database.limitToLast(1).on('value', function(snapshot) {
    for(var i in snapshot.val()){
       $chatWindow.append(snapshot.val()[i].time + '&nbsp&nbsp' + snapshot.val()[i].name + ' 說: ' + snapshot.val()[i].content + "<BR>");
       
    }
    /*
     //如果是自己發出去的文字，就改變顏色
    $chatWindow.find('.id' + ms).css({
      color: '#f00'
    });
    $chatWindow.find('.id' + ms + ' div').css({
      color: '#f00'
    });
    */
  });
  
  
});