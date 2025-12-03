package driod.dicex;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * DriodDiceXActivity
 * @author yasupong
 */
@SuppressLint("SimpleDateFormat")
public class DriodDiceXActivity extends Activity implements OnItemSelectedListener, OnClickListener {

    /** タイトル */
	private static final int GAME_TITLE = 0;
    /** ゲーム終了 */
	private static final int GAME_END = 2;
    /** スコア(1) */
	private static final int SCR_1 = 0;
    /** スコア(2) */
	private static final int SCR_2 = 1;
    /** スコア(3) */
	private static final int SCR_3 = 2;
    /** スコア(4) */
	private static final int SCR_4 = 3;
    /** スコア(5) */
	private static final int SCR_5 = 4;
    /** スコア(6) */
	private static final int SCR_6 = 5;
    /** スコア(3card) */
	private static final int SCR_3CARD = 6;
    /** スコア(4card) */
	private static final int SCR_4CARD = 7;
    /** スコア(Full) */
	private static final int SCR_FULL = 8;
    /** スコア(SStght) */
	private static final int SCR_SSTGHT = 9;
    /** スコア(LStght) */
	private static final int SCR_LSTGHT = 10;
	/** スコア(Chance) */
	private static final int SCR_CHANCE = 11;
    /** スコア(DiceX) */
	private static final int SCR_DICEX = 12;
    /** ペアチェック(3card) */
	private static final int PCK_3CARD = 1;
    /** ペアチェック(4card) */
	private static final int PCK_4CARD = 2;
    /** ペアチェック(Full) */
	private static final int PCK_FULL = 3;
    /** ペアチェック(DiceX) */
	private static final int PCK_DICEX = 4;
	
	/** メニューアイテムID0 */
	private final int MENU_ITEM0 = 0;
	/** メニューアイテムID2 */
	private final int MENU_ITEM2 = 2;
	
    /** 乱数 */
	private Random rnd = new Random();
    /** スコア表示用配列 */
	private String SCORE_DISP[] = new String[13];
    /** ゲームステータス(初期状態はタイトル) */
	private int gameStatus = GAME_TITLE;
    /** サイコロ交換可能回数 */
	private int chance = 3;
    /** サイコロ値 */
	private int cardnum[] = null;
    /** 現在選択されたスコア */
	private int currentscorechkd = 0;
    /** スコア1 */
	private int selectedscr1 = 0;
    /** スコア2 */
	private int selectedscr2 = 0;
    /** スコア選択可能回数 */
	private int gameleft = 13;
    /** ボーナススコア用 */
	private int bonuscount = 0;
    /** 合計点 */
	private int total = 0;
    /** カードホールドフラグ */
	private boolean hold[] = null;
    /** 使用済みスコアフラグ */
	private boolean scoreused[] = null;
    /** 取得済みスコアフラグ */
	private int scoreget[] = null;
    /** ボーナス確認フラグ */
    private boolean bonusflag = false;
    
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 //       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
//        LinearLayout mainlinearLayout = (LinearLayout)this.findViewById(R.id.MainlinearLayout);
//        ViewGroup.LayoutParams resize_param = mainlinearLayout.getLayoutParams();
//        Log.d ("DEBUG", String.valueOf(resize_param.width));
//        Log.d ("DEBUG", String.valueOf(resize_param.height));
        
        // 初期化
        init();

        // スコアプルダウンの生成
        refreshSpinnerScore();
        Spinner spinner = (Spinner) findViewById(R.id.spinnerScore);
        spinner.setOnItemSelectedListener(this);

        // ボタンイベントリスナー設定
        Button btnRoll = (Button) findViewById(R.id.buttonRoll);
        btnRoll.setOnClickListener(this);
        Button btnEnter = (Button) findViewById(R.id.buttonEnter);
        btnEnter.setOnClickListener(this);
        Button btnStatus = (Button) findViewById(R.id.buttonStatus);
        btnStatus.setOnClickListener(this);

        // さいころの表示
        dispImageViewDice();

        // テキストの表示
	    TextView txtTotal = (TextView) findViewById(R.id.textTotal);
	    txtTotal.setText(getString(R.string.msg_score) + String.valueOf(total));
	    TextView txtBonus = (TextView) findViewById(R.id.textViewBonus);
        btnRoll.setText(getString(R.string.wid_roll) + "(" + chance + ")");
	    txtBonus.setText("");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	/**
	 * 終了時の初期化
	 */
	private void endinit() {
    	//初期化
    	init();
    	refreshSpinnerScore();
        // さいころの表示
        dispImageViewDice();

        // テキストの表示
	    TextView txtTotal = (TextView) findViewById(R.id.textTotal);
	    txtTotal.setText(getString(R.string.msg_score) + String.valueOf(total));
	    TextView txtBonus = (TextView) findViewById(R.id.textViewBonus);
        Button btnRoll = (Button) findViewById(R.id.buttonRoll);
        btnRoll.setText(getString(R.string.wid_roll) + "(" + chance + ")");
	    txtBonus.setText("");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case MENU_ITEM0:
				saveScoreLog();
				return true;
			case MENU_ITEM2:
		        Intent intent = new Intent(DriodDiceXActivity.this, DriodDiceXActivity.class);
		        startActivity(intent);
				return true;
		}
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuItem actionItem0 = menu.add(0, MENU_ITEM0, 0, getString(R.string.menu_export));
		actionItem0.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		
		MenuItem actionItem2 = menu.add(0, MENU_ITEM2, 0, getString(R.string.menu_reset));
		actionItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		
		return true;
	}
	
	public void onClick(View v) {

        Button btnRoll = (Button) findViewById(R.id.buttonRoll);
        Button btnEnter = (Button) findViewById(R.id.buttonEnter);
        Button btnStatus = (Button) findViewById(R.id.buttonStatus);
        
        // ROLLボタン押下の場合
		if (v == btnRoll) {
			// チェックボックスのスキャン
			scanCheckBox();
			// さいころを振る
			flushCards();
	        // さいころの表示
	        dispImageViewDice();

	        chance--;

	        btnRoll.setText(getString(R.string.wid_roll) + "(" + chance + ")");

    		// スコアを計算する
    		checkScore1();
    		checkScore2();

    	    TextView txtSelected = (TextView) findViewById(R.id.textSelected);
    		if (currentscorechkd < SCR_3CARD) {
    			txtSelected.setText(getString(R.string.msg_rollvalue) + String.valueOf(selectedscr1));
    		}
    		else {
    			txtSelected.setText(getString(R.string.msg_rollvalue) + String.valueOf(selectedscr2));
    		}

			if (scoreused[currentscorechkd]) {
				txtSelected.setText( getString(R.string.msg_rollvalue) + "*" );
			}
			
	        if (chance == 0) {
	        	btnRoll.setClickable(false);
	        }
		}
		// ENTERボタン押下の場合
		else if (v == btnEnter) {

			// 使用済みスコア警告ダイアログ
			if (scoreused[currentscorechkd]){
				AlertDialog.Builder dlgChecked = new AlertDialog.Builder(this);
				dlgChecked.setPositiveButton("OK", null);
				dlgChecked.setMessage(getString(R.string.msg_picked));
				dlgChecked.show();
				return;
			}

			// スコア入力
			enterScore();

			// 表示の更新
		    TextView txtTotal = (TextView) findViewById(R.id.textTotal);
		    txtTotal.setText(getString(R.string.msg_score) + String.valueOf(total));

		    // ボーナス取得済みか
		    if (bonusflag) {
			    TextView txtBonus = (TextView) findViewById(R.id.textViewBonus);
			    txtBonus.setText(getString(R.string.msg_bonus));
		    }

		    // ロールボタン表示更新
		    chance = 3;
	        btnRoll.setText(getString(R.string.wid_roll) + "(" + chance + ")");
        	btnRoll.setClickable(true);

			// さいころを振る
			flushCards();
	        // さいころの表示
	        dispImageViewDice();

		    Spinner spinner = (Spinner)findViewById(R.id.spinnerScore);
		    spinner.setSelection(currentscorechkd);

    		// スコアを計算する
    		checkScore1();
    		checkScore2();

    	    TextView txtSelected = (TextView) findViewById(R.id.textSelected);
    		if (currentscorechkd < SCR_3CARD) {
    			txtSelected.setText(getString(R.string.msg_rollvalue) + String.valueOf(selectedscr1));
    		}
    		else {
    			txtSelected.setText(getString(R.string.msg_rollvalue) + String.valueOf(selectedscr2));
    		}

		    initCheckBox();

		    // ゲーム終了時
		    if (gameStatus == GAME_END) {
		    	// データ保存
		    	saveScore(total);
		    	// ダイアログ表示
				AlertDialog.Builder dlgEnd = new AlertDialog.Builder(this);
				
				dlgEnd.setIcon(android.R.drawable.ic_dialog_alert);
		        dlgEnd.setTitle(getString(R.string.msg_gameover));
		        dlgEnd.setMessage(getString(R.string.msg_result) + total);
		        dlgEnd.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		            	endinit();
		            }
		        });
		        dlgEnd.setNegativeButton(getString(R.string.msg_export), new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		        		Intent intent = new Intent(Intent.ACTION_SEND);
		        		intent.setType("text/plain");
		        		
		    	        String msg = "";
		    	    	msg = msg + getString(R.string.msg_score) + total;	
		    	    	msg = msg + " #DROIDDICEX";	
		    	    	
		        		intent.putExtra(Intent.EXTRA_TEXT, msg);  
		        		startActivity(Intent.createChooser(intent, getString(R.string.msg_export)));
		            	endinit();
		            }
		        });
		        dlgEnd.show();
				return;
		    }
		}
		// STATUSボタン押下の場合
		else if (v == btnStatus) {
			
			AlertDialog.Builder dlgStatus = new AlertDialog.Builder(this);
			
	        String msg = "";
	    	for (int i = 0; i < SCORE_DISP.length; i++) {
	    		msg = msg + SCORE_DISP[i];
	    		if (scoreused[i]) {
	    			msg = msg + " * ";
	    			msg = msg + scoreget[i];
	    		}
    			msg = msg + "\n";
			}
	    	
	    	if (bonusflag) {
    			msg = msg + getString(R.string.msg_bonus_get);	
	    	}
	    	
	    	dlgStatus.setPositiveButton("OK", null);
	    	dlgStatus.setIcon(android.R.drawable.ic_dialog_info);
	    	dlgStatus.setTitle(getString(R.string.msg_status));
			dlgStatus.setMessage(msg);
    		dlgStatus.show();
			return;
		}
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	    Spinner spinner = (Spinner) findViewById(R.id.spinnerScore);
        String item = (String) spinner.getSelectedItem();

	    TextView txtSelected = (TextView) findViewById(R.id.textSelected);
	    String selected = getString(R.string.msg_rollvalue);

        for (int i = 0; i < SCORE_DISP.length; i++) {
        	if (SCORE_DISP[i].equals(item)) {
        		currentscorechkd = i;
        		// スコアを計算する
        		checkScore1();
        		checkScore2();

        		if (i < SCR_3CARD) {
        			selected = selected + selectedscr1;
        		}
        		else {
        			selected = selected + selectedscr2;
    			}

				if (scoreused[currentscorechkd]) {
					selected = getString(R.string.msg_rollvalue) + "*";
				}

				break;
        	}
		}

        txtSelected.setText(selected);
	}

	public void onNothingSelected(AdapterView<?> arg0) {
	}
	
	/**
	 * スコアプルダウンの生成
	 */
	private void refreshSpinnerScore() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SCORE_DISP[SCR_1] = getString(R.string.scr_1);
        SCORE_DISP[SCR_2] = getString(R.string.scr_2);
        SCORE_DISP[SCR_3] = getString(R.string.scr_3);
        SCORE_DISP[SCR_4] = getString(R.string.scr_4);
        SCORE_DISP[SCR_5] = getString(R.string.scr_5);
        SCORE_DISP[SCR_6] = getString(R.string.scr_6);
        SCORE_DISP[SCR_3CARD] = getString(R.string.scr_3card);
        SCORE_DISP[SCR_4CARD] = getString(R.string.scr_4card);
        SCORE_DISP[SCR_FULL] = getString(R.string.scr_full);
        SCORE_DISP[SCR_SSTGHT] = getString(R.string.scr_sstght);
        SCORE_DISP[SCR_LSTGHT] = getString(R.string.scr_lstght);
        SCORE_DISP[SCR_CHANCE] = getString(R.string.scr_chance);
        SCORE_DISP[SCR_DICEX] = getString(R.string.scr_dicex);
        
        for (int i = 0; i < SCORE_DISP.length; i++) {
        	adapter.add(SCORE_DISP[i]);
		}

        Spinner spinner = (Spinner) findViewById(R.id.spinnerScore);
        spinner.setAdapter(adapter);
	}

	/**
	 * さいころを表示する
	 */
	private void dispImageViewDice() {
		ImageView imgDice1 = (ImageView)findViewById(R.id.imageViewDice1);
		imgDice1.setImageResource(getImageId(cardnum[0]));
		ImageView imgDice2 = (ImageView)findViewById(R.id.imageViewDice2);
		imgDice2.setImageResource(getImageId(cardnum[1]));
		ImageView imgDice3 = (ImageView)findViewById(R.id.imageViewDice3);
		imgDice3.setImageResource(getImageId(cardnum[2]));
		ImageView imgDice4 = (ImageView)findViewById(R.id.imageViewDice4);
		imgDice4.setImageResource(getImageId(cardnum[3]));
		ImageView imgDice5 = (ImageView)findViewById(R.id.imageViewDice5);
		imgDice5.setImageResource(getImageId(cardnum[4]));
	}

	/**りｆ
	 * チェックボックス状態のスキャン
	 */
	private void scanCheckBox() {
        CheckBox checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        CheckBox checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        CheckBox checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        CheckBox checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
        CheckBox checkBox5 = (CheckBox)findViewById(R.id.checkBox5);

        hold[0] = checkBox1.isChecked();
        hold[1] = checkBox2.isChecked();
        hold[2] = checkBox3.isChecked();
        hold[3] = checkBox4.isChecked();
        hold[4] = checkBox5.isChecked();
	}

	/**
	 * チェックボックス初期化
	 */
	private void initCheckBox(){
        CheckBox checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        CheckBox checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        CheckBox checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        CheckBox checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
        CheckBox checkBox5 = (CheckBox)findViewById(R.id.checkBox5);

        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        checkBox5.setChecked(false);
	}

	/**
	 * イメージIDを取得する
	 * @param cardNum
	 * @return
	 */
	private int getImageId(int cardNum) {
		switch (cardNum) {
		case 0:
			return R.drawable.one;
		case 1:
			return R.drawable.two;
		case 2:
			return R.drawable.three;
		case 3:
			return R.drawable.four;
		case 4:
			return R.drawable.five;
		case 5:
			return R.drawable.six;
		default:
			return 99;
		}
	}

	/**
	 * 初期化
	 */
	private void init() {

		//変数の初期化
		hold = new boolean[5];
		scoreused = new boolean[13];
		scoreget = new int[13];
		cardnum = new int[5];
		gameStatus = GAME_TITLE;
		bonuscount = 0;
		total = 0;
		chance = 3;
	    gameleft = 13;
		bonusflag = false;
		
		// さいころをまわす
		flushCards();

		// 初期状態のスコアを計算する
		checkScore1();
		checkScore2();
	}

	/**
	 * サイコロの値を得るための乱数取得関数(サブルーチン)
	 */
	private void flushCards() {
		for (int i = 0; i < 5; i++) {
			if (!hold[i]) {
	        cardnum[i] = Math.abs(rnd.nextInt()) % 6;
	      }
	  }
	}

	/**
	 * スコアチェック関数1(サブルーチン)
	 * サイコロの目別の合計のスコア選択時のスコアを計算する
	 */
	private void checkScore1() {
	  //1の出目選択時
	  if (currentscorechkd == SCR_1) {
	    selectedscr1 = addNumbersScore(1, SCR_1, cardnum);
	  //2の出目選択時
	  } else if (currentscorechkd == SCR_2) {
	    selectedscr1 = addNumbersScore(2, SCR_2, cardnum);
	  //3の出目選択時
	  } else if (currentscorechkd == SCR_3) {
	    selectedscr1 = addNumbersScore(3, SCR_3, cardnum);
	  //4の出目選択時
	  } else if (currentscorechkd == SCR_4) {
	    selectedscr1 = addNumbersScore(4, SCR_4, cardnum);
	  //5の出目選択時
	  } else if (currentscorechkd == SCR_5) {
	    selectedscr1 = addNumbersScore(5, SCR_5, cardnum);
	  //6の出目選択時
	  } else if (currentscorechkd == SCR_6) {
	    selectedscr1 = addNumbersScore(6, SCR_6, cardnum);
	  //この状態の時はペア系・ストレート系のスコアをチェック中
	  } else {
	    selectedscr1 = 0;
	  }
	}

	/**
	 * 出目系スコア足しこみ関数
	 * @param actscr 目別の点数
	 * @param cdnum サイコロの出目種類
	 * @param inscr 入力されたサイコロの出目
	 * @return スコアの返却
	 */
	private int addNumbersScore(int actscr,int cdnum,int inscr[]) {
	  int scre=0;
	  for (int i = 0; i < 5; i++) {
	    //さいの目が同じものだけ足しこみ
	    if (inscr[i] == cdnum) {
	      scre+=actscr;
	    }
	  }
	  return(scre);
	}

	/**
	 * スコアチェック関数2(サブルーチン)
	 * ペア系・ストレート系のスコアをチェックする
	 */
    private void checkScore2() {
      //サブスコア格納配列
      int subscr[] = new int[8];
      //ホールドスコア
      int hldscr = 0;
      //ペア系チェックステータス
      int pairChkdStatus = 0;
      //サブスコア格納配列へコピー
      for (int c=0;c<5;c++) {
        subscr[c] = cardnum[c];
      }
      //ソートを行う(バブルソート)
       for (int i = 0; i < 4; i++) {
         for (int ii = 0; ii < 4; ii++) {
           int first = subscr[ii];
           int second = subscr[ii + 1];

           if (first > second) {
             subscr[ii] = second;
             subscr[ii+1] = first;
           }
         }
      }
      //ソート済のサブスコアでペア系チェックを行う
      pairChkdStatus = rtnCheckPair(subscr);
      //スリーカード選択時
      if (currentscorechkd == SCR_3CARD) {
        //全てのステータスが存在する場合必ずスリーカードは成立している
        if (pairChkdStatus == PCK_3CARD || pairChkdStatus == PCK_4CARD || pairChkdStatus == PCK_FULL || pairChkdStatus == PCK_DICEX) {
          for (int i12 = 0; i12 < 5; i12++) {
            hldscr+=subscr[i12];
            hldscr++;
          }
          selectedscr2 = hldscr;
        } else {
          selectedscr2 = 0;
        }
      //フォーカード選択時
      } else if (currentscorechkd == SCR_4CARD) {
        //フォーカードかDiceXのステータスが存在する場合
        if (pairChkdStatus == PCK_4CARD || pairChkdStatus == PCK_DICEX) {
          for (int i22 = 0; i22 < 5; i22++) {
            hldscr+=subscr[i22];
            hldscr++;
          }
          selectedscr2 = hldscr;
        } else {
          selectedscr2 = 0;
        }
      //フルハウス選択時
      } else if (currentscorechkd == SCR_FULL) {
        //フルハウスのステータスが存在する場合
        if (pairChkdStatus == PCK_FULL) {
          selectedscr2 = 25;
        } else {
          selectedscr2=0;
        }
      //小ストレート選択時
      } else if (currentscorechkd == SCR_SSTGHT) {
        int smstraightflag=0;
        for (int i4 = 0; i4 < 4; i4++) {
          if (subscr[i4] == (subscr[i4 + 1] - 1)) {
            smstraightflag++;
          }
        }
        if (smstraightflag > 2) {
          selectedscr2 = 30;
        } else {
          selectedscr2 = 0;
        }
      //大ストレート選択時
      } else if (currentscorechkd == SCR_LSTGHT) {
        int lgstraightflag=0;
        for (int i5 = 0; i5 < 4; i5++) {
          if (subscr[i5] == (subscr[i5 + 1] - 1)) {
            lgstraightflag++;
          }
        }

        if (lgstraightflag == 4) {
          selectedscr2 = 40;
        } else {
          selectedscr2 = 0;
        }
      //チャンス選択時
      } else if (currentscorechkd == SCR_CHANCE) {
        for (int i6 = 0; i6 < 5; i6++) {
          hldscr+=subscr[i6];
          hldscr++;
        }
        selectedscr2=hldscr;
      //DiceX選択時
      } else if (currentscorechkd == SCR_DICEX) {
        //DiceXのステータスが存在する場合
        if (pairChkdStatus == PCK_DICEX) {
          selectedscr2 = 50;
        } else {
          selectedscr2 = 0;
        }
      //この状態の時はサイコロの目別の合計のスコアをチェック中
      } else {
        selectedscr2 = 0;
      }
    }

	/**
	 * ペアチェック関数
	 * @param incd 入力カード(必ずソート済であること)
	 * @return スコア識別子返却
	 */
	private int rtnCheckPair(int incd[]) {
	  //ペアカウント
	  int pair = 0;
	  //スリーカードフラグ
	  boolean threecardflag = false;
	  //フォーカードフラグ
	  boolean fourcardflag = false;
	  //DiceXフラグ
	  boolean yahtflag = false;
	  //先頭4枚のループ
	  for (int i = 0; i < 4; i++) {
	    if (incd[i] == incd[i + 1]) {
	      //ペアカウントアップ
	      pair++;
	      //ペアが1つ有りかつ2つ先に同じカードがある場合
	      if ((pair == 1) && (incd[i] == incd[i + 2])) {
	        //スリーカード有り決定
	        threecardflag = true;
	        //スリーカードが有りかつ3つ先に同じカードがある場合
	        if (threecardflag && (incd[i] == incd[i + 3])) {
	          //フォーカード有り決定
	          fourcardflag = true;
	          //フォーカードがありかつ4つ先に同じカードがある場合
	          if (fourcardflag && (incd[i] == incd[i + 4])) {
	            //DiceX有り決定
	            yahtflag = true;
	          }
	        }
	      }
	    }
	  }
	  //DiceX有り時
	  if (yahtflag) {
	    return(PCK_DICEX);
	  }
	  //フォーカード有り時
	  if (fourcardflag) {
	    return(PCK_4CARD);
	  //フォーカード有りでなくかつペアカウントが3のとき
	  //(スリーカードはあるかもしれないがペアカウントが3つある時は
	  //フォーカードかフルハウスしかありえない)
	  //フルハウスの場合は必ずスリーカードが成立するので
	  //このように制御している
	  } else if (!fourcardflag && pair == 3) {
	    return(PCK_FULL);
	  //スリーカード有り時
	  } else if (threecardflag) {
	    return(PCK_3CARD);
	  //何もあてはまらない時
	  } else {
	    return(0);
	  }
	}

	/**
	 * スコア入力関数(サブルーチン)
	 */
	private void enterScore() {

	  //現在選択されているスコアが使用されていない場合
	  if (!scoreused[currentscorechkd]) {
	    total+=selectedscr1;
	    total+=selectedscr2;
	    scoreget[currentscorechkd] = selectedscr1 + selectedscr2;
	    bonuscount+=selectedscr1;

	    //ボーナスのチェック
	    if (bonuscount > 62 && !bonusflag) {
	      total = total + 35;
	      bonusflag = true;
	    }

	    gameleft--;
	    scoreused[currentscorechkd] = true;
	    chance = 3;

	    //使用されていないスコアを選択するまでループ
	    while (scoreused[currentscorechkd] && currentscorechkd < SCR_DICEX) {
	      currentscorechkd++;
	    }

	    //さいのホールドの解除
	    for (int i = 0; i < 5;i++) {
	      hold[i] = false;
	    }
	  }
	  //残りゲームが0の場合ゲームを終了させる
	  if (gameleft == 0) {
	    gameStatus = GAME_END;
	  }
	}
	
	/**
	 * スコアのセーブ
	 * @param score
	 */
	private void saveScore(int score) {
		// DB更新
		DriodDiceXDBHelper dbHelper = new DriodDiceXDBHelper(getBaseContext());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		try {
			ContentValues values = new ContentValues();
			values.put("scoredate", System.currentTimeMillis());
			values.put("score", score);
			db.insert(DriodDiceXDBHelper.DB_TABLE_SCORE, null, values);
		}
		finally {
			db.close();
		}
	}
	
	/**
	 * DB全件検索
	 * @return
	 */
	private List<DriodDiceXScoreEntity> getAllcScoreList() {
		DriodDiceXDBHelper dbHelper = new DriodDiceXDBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cScore = db.query(DriodDiceXDBHelper.DB_TABLE_SCORE, new String[]{ "scoredate","score" }, null, null, null, null, "scoredate desc");
        
        List<DriodDiceXScoreEntity> resultList = new ArrayList<DriodDiceXScoreEntity>();

		SimpleDateFormat sdfDB = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
        // リストに落とし込む
        while (cScore.moveToNext()){
            DriodDiceXScoreEntity score = new DriodDiceXScoreEntity();
            score.setScoredate(sdfDB.format(new Date(Long.parseLong(cScore.getString(0)))));
            score.setScore(cScore.getString(1));
        	resultList.add(score);
        }
        
        cScore.close();
        db.close();
        
        return resultList;
	}
	
	/**
	 * スコア記録保存
	 */
	private void saveScoreLog() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		
		String text = "";
        List<DriodDiceXScoreEntity> resultList = getAllcScoreList();
        
        // ログリスト作成
		for (Iterator<DriodDiceXScoreEntity> iterator = resultList.iterator(); iterator.hasNext();) {
			DriodDiceXScoreEntity score = (DriodDiceXScoreEntity) iterator.next();
			text = text + score.getScoredate() + "," + score.getScore() + "\n";
		}
		
		intent.putExtra(Intent.EXTRA_TEXT, text);  
		startActivity(Intent.createChooser(intent, getString(R.string.menu_export)));
	}
}