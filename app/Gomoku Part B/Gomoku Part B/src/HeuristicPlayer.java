//Georgia Pantalona    8537    6988757893   pantalona@auth.gr
//Georgia Petridou     7964    6987791812   g.petridou@hotmail.com

import java.util.ArrayList;
//edw ginetai sumperilipsh ths java.util.ArrayList  gia na mporw na xrhsimopoihsw thn klash ston kwdika mou


public class HeuristicPlayer implements AbstractPlayer
{
  int score;
  int id;
  String name;

  public HeuristicPlayer (Integer pid)
  {
    id = pid;
    score = 0;

  }
 
  public HeuristicPlayer(Integer pid, int pscore, String pname){
	  score = pscore;
	  id = pid;
	  name=pname;
	  
  }

  public String getName ()
  {

    return "Heuristic";

  }

  public int getId ()
  {
    return id;
  }

  public void setScore (int score)
  {
    this.score = score;
  }

  public int getScore ()
  {
    return score;
  }

  public void setId (int id)
  {
   
    this.id = id;

  }

  public void setName (String name)
  {
    
    this.name = name;

  }

  public int[] getNextMove (Board board)
  {
    ArrayList<double[]> thisArrayList = new ArrayList <double[]>();
//dhmiourgia mias arraylist pou kathe grammh tha einai enas pinakas tupou double : ara dhmirourgw disdiastato pinaka
   
    for(int i=0;i<GomokuUtilities.NUMBER_OF_ROWS;i++){
    	for(int j=0;j<GomokuUtilities.NUMBER_OF_COLUMNS;j++){
    		//gia kathe ena plakidio tou tamplo
    		if(board.getTile(i,j).getColor()==0){
    			//an brw oti einai eleuthero
    			double eval;
        		eval=evaluate(i,j,board);
        		//zhtaw poso aksizei na topothetithei se auto, to pouli tou paixth
        		double ar[]={0,0,0};
        		//dhmiourgia tou monodiastatou ar  pou exei 3 kelia
        		ar[0]=i;//sto 1o keli phgainei h tetmhmenh tou plakidiou pou eksetazetai
        		ar[1]=j;//sto 2o keli phgainei h tetagmenh tou plakidiou pou eksetazetai
        		ar[2]=eval;//sto 3o keli h aksia tou plakidiou pou eksetazetai
        		thisArrayList.add(ar); 
        		//kai prosthetw ton ar ws mia grammh ths arraylist
        		
    		}
    		
    	}
    }
    //afou exei apothikeutei sta kelia h aksia kathe plakidiou
    //tha ginei anazhthsh ths kaluterhs!
    int k=0;    
    double maxx,maxy,m; //boithikes metablhtes kai arxikopoihsh tous
    maxx=thisArrayList.get(k)[0];  //h tetmhmenh tou prwtou eleutherou plakidiou pou entopisthke
    maxy=thisArrayList.get(k)[1];  //h tetagmenh tou prwtou eleutherou plakidiou pou entopisthke
    m=thisArrayList.get(k)[2];     //h aksia tou prwtou eleutherou plakidiou pou entopisthke
    //elegxos gia kathe ena apo ta upoloipa
    for(k=1;k<thisArrayList.size();k++){
    	if (thisArrayList.get(k)[2]>m){   //an h aksia kapoiou plakidiou kseperasei thn katagegrammenh
    		m=thisArrayList.get(k)[2];   //ginetai antikatastash 
    	    maxx=thisArrayList.get(k)[0];
    	    maxy=thisArrayList.get(k)[1];
    	}
    }
    int sun[] = new int[2]; //shmiourgeitai o sun (=suntetagmenes) //o opoios periexei 
	sun[0]= (int)maxx;  //sto 1o keli thn tetmhmenh tou kaluterou plakidiou
	sun[1]=(int)maxy;  //sto 2o keli thn tetagmenh tou kaluterou plakidiou
	board.printBoard();
	return sun;  //o opoios kai epistrefetai gia na oloklhrwthei h epomenh kinhsh
	
  }
	
  boolean createsQuintuple(int x,int y,Board board,int idp){
//h sunarthsh stoxeuei sthn euresh tetradas pouliwn tou paixth
	  int i;
	  //katheta
	  int o=0;       // metrhths gia to posa poulia tou paixth tha brei sthn seira
	  boolean fl=true; //flag=shmaia
	  do{
		  //pros ta katw
		  for(i=1;i<=4;i++){
			  if(x-i>=0){      //elegxos mhn bgei eksw apo ta oria //einai mesa????
				  if(board.getTile(x-i,y).getColor()==idp){   //exeis pouli sto plakidio (x-i,y) ???
					  o++;
					  //o metrhths auksanetai
				  }
				  else{
					  break;
					  //alliws stamataei ton elegxo twn pros ta pisw plakidiwn
				  }
			  }
			  else{ 
				  break;
				  //an bghkes apo ta oria tou tamplo,stamatei ton elegxo twn pros ta pisw plakidiwn
			  }
		  }
		  //pros ta panw
		  //an den exeis brei akoma 4 sunexomena plakidia me to pouli tou paixth
		  if(o<4){
			  i=1;
			  while(true){
				  if(x+i<GomokuUtilities.NUMBER_OF_ROWS){  //elegxos mh bgei eksw apo oria //einai mesa??
					  if(board.getTile(x+i,y).getColor()==idp){   //exei pouli sto plakidio (x+i,y) ???
						  o++;
						  //o metrhths auksanetai
					  }
					  else{
						  fl=false;
						  //alliws h shmaia deixnei sthn ekswterikh do_while na spasei
						  break;
						  //kai stamataei o elegxos pros ta mpros
					  }
					  //eftase edw ara o metrhths ontws auksithike
					  //elegxos an o metrhths sumplhrwse tetrada h an elegxthikan kai ta 4 plakidia pros ta empros
					  if(o==4 || i==4){
						  fl=false;
						  //h shmaia deixnei sthn ekswterikh do_while na spasei
						  break;
						  //kai stamataei o elegxos pros ta mpros
					  }
					  i++;   //enallaktika tha elegxthei to epomeno plakidio
				  }
				  else{
					  fl=false;
					//an bghkes apo ta oria tou tamplo,h shmaia deixnei sthn ekswterikh do_while na spasei
					  break;
					//kai stamatei o elegxos twn pros ta pisw plakidiwn
				  }
				  
			  }
		  }
		  
	  }while(o<4 && fl);  //h epanalhpsh spaei otan sumplhrwthei tetrada h otan h shmaia to upagoreusei
	  //an h tetrada ontws sumplhrwthike 
	  if(o==4){
		  return true;
		  //epistrefei thetikh apanthsh
	  }
	  //enallaktika sunexizetai o elegxos
	  //orizontia                           //opou akoloutheitai h antistoixh logikh
	  o=0;
	  fl=true;
	  do{
		  //pros ta pisw
		  for(i=1;i<=4;i++){
			  if(y-i>=0){
				  if(board.getTile(x,y-i).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
			  
		  }
		  //pros ta mpros
		  if(o<4){
			  i=1;
			  while(true){
				  if(y+i<GomokuUtilities.NUMBER_OF_COLUMNS){
					  if(board.getTile(x,y+i).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==4 || i==4){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
			  }
		  }
		  
	  }while(o<4 && fl);
	  if(o==4){
		  return true;
	  }
	  //anodika                       //kathws kai edw
	  o=0;
	  fl=true;
	  do{
		  //pros ta pisw
		  for(i=1;i<=4;i++){
			  if(x-i>=0 && y-i>=0){
				  if(board.getTile(x-i,y-i).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
		  }
		  //pros ta mpros
		  if(o<4){
			  i=1;
			  while(true){
				  if(x+i<GomokuUtilities.NUMBER_OF_ROWS && y+i<GomokuUtilities.NUMBER_OF_COLUMNS){
					  if(board.getTile(x+i,y+i).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==4 || i==4){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
			  }
		  }
		  
	  }while(o<4 && fl);
	  if(o==4){
		  return true;
	  }
	  //kathodika                  //kai edw paromoiws
	  o=0;
	  fl=true;
	  do{
		  //pros ta mpros
		  for(i=1;i<=4;i++){
			  if(x-i>=0 && y+i<GomokuUtilities.NUMBER_OF_COLUMNS){
				  if(board.getTile(x-i,y+i).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
		  }
		  //pros ta pisw
		  if(o<4){
			  i=1;
			  while(true){
				  if(x+i<GomokuUtilities.NUMBER_OF_ROWS && y-i>0){
					  if(board.getTile(x+i,y-i).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==4 || i==4){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
			  }
		  }
		  
	  }while(o<4 && fl);
	  if(o==4){
		  return true;
	  }
	  else{
		  return false;
	  }
  }
  
  boolean createsQuartiple(int x,int y,Board board,int idp){
	  //idia logikh akoloutheitai kai se auth th sunarthsh mono pou stoxeuei sthn euresh triadas tou paixth
	  int i;
	  //katheta
	  int o=0;
	  boolean fl=true;
	  do{
		  //pros ta katw
		  for(i=1;i<=3;i++){
			  if(x-i>=0){
				  if(board.getTile(x-i,y).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
		  }
		  //pros ta panw
		  if(o<3){
			  i=1;
			  while(true){
				  if(x+i<GomokuUtilities.NUMBER_OF_ROWS){
					  if(board.getTile(x+i,y).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==3 || i==3){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
				  
			  }
		  }
		  
	  }while(o<3 && fl);
	  if(o==3){
		  return true;
	  }
	  //orizontia
	  o=0;
	  fl=true;
	  do{
		  //pros ta pisw
		  for(i=1;i<=3;i++){
			  if(y-i>=0){
				  if(board.getTile(x,y-i).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
			  
		  }
		  //pros ta mpros
		  if(o<3){
			  i=1;
			  while(true){
				  if(y+i<GomokuUtilities.NUMBER_OF_COLUMNS){
					  if(board.getTile(x,y+i).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==3 || i==3){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
			  }
		  }
		  
	  }while(o<3 && fl);
	  if(o==3){
		  return true;
	  }
	  //anodika
	  o=0;
	  fl=true;
	  do{
		  //pros ta pisw
		  for(i=1;i<=3;i++){
			  if(x-i>=0 && y-i>=0){
				  if(board.getTile(x-i,y-i).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
		  }
		  //pros ta mpros
		  if(o<3){
			  i=1;
			  while(true){
				  if(x+i<GomokuUtilities.NUMBER_OF_ROWS && y+i<GomokuUtilities.NUMBER_OF_COLUMNS){
					  if(board.getTile(x+i,y+i).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==3 || i==3){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
			  }
		  }
		  
	  }while(o<3 && fl);
	  if(o==3){
		  return true;
	  }
	  //kathodika
	  o=0;
	  fl=true;
	  do{
		  //pros ta mpros
		  for(i=1;i<=3;i++){
			  if(x-i>=0 && y+i<GomokuUtilities.NUMBER_OF_COLUMNS){
				  if(board.getTile(x-i,y+i).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
		  }
		  //pros ta pisw
		  if(o<3){
			  i=1;
			  while(true){
				  if(x+i<GomokuUtilities.NUMBER_OF_ROWS && y-i>=0){
					  if(board.getTile(x+i,y-i).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==3 || i==3){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
			  }
		  }
		  
	  }while(o<3 && fl);
	  if(o==3){
		  return true;
	  }
	  else{
		  return false;
	  }
	  
  }
  
  boolean createsTriple(int x,int y,Board board,int idp){
	//idia logikh akoloutheitai kai se auth th sunarthsh mono pou stoxeuei sthn euresh duadas tou paixth
	  int i;
	  //katheta
	  int o=0;
	  boolean fl=true;
	  do{
		  //pros ta katw
		  for(i=1;i<=2;i++){
			  if(x-i>=0){
				  if(board.getTile(x-i,y).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
		  }
		  //pros ta panw
		  if(o<2){
			  i=1;
			  while(true){
				  if(x+i<GomokuUtilities.NUMBER_OF_ROWS){
					  if(board.getTile(x+i,y).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==2 || i==2){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
				  
			  }
		  }
		  
	  }while(o<2 && fl);
	  if(o==2){
		  return true;
	  }
	  //orizontia
	  o=0;
	  fl=true;
	  do{
		  //pros ta pisw
		  for(i=1;i<=2;i++){
			  if(y-i>=0){
				  if(board.getTile(x,y-i).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
			  
		  }
		  //pros ta mpros
		  if(o<2){
			  i=1;
			  while(true){
				  if(y+i<GomokuUtilities.NUMBER_OF_COLUMNS){
					  if(board.getTile(x,y+i).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==2 || i==2){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
			  }
		  }
		  
	  }while(o<2 && fl);
	  if(o==2){
		  return true;
	  }
	  //anodika
	  o=0;
	  fl=true;
	  do{
		  //pros ta pisw
		  for(i=1;i<=2;i++){
			  if(x-i>=0 && y-i>=0){
				  if(board.getTile(x-i,y-i).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
		  }
		  //pros ta mpros
		  if(o<2){
			  i=1;
			  while(true){
				  if(x+i<GomokuUtilities.NUMBER_OF_ROWS && y+i<GomokuUtilities.NUMBER_OF_COLUMNS){
					  if(board.getTile(x+i,y+i).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==2 || i==2){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
			  }
		  }
		  
	  }while(o<2 && fl);
	  if(o==2){
		  return true;
	  }
	  //kathodika
	  o=0;
	  fl=true;
	  do{
		  //pros ta mpros
		  for(i=1;i<=2;i++){
			  if(x-i>=0 && y+i<GomokuUtilities.NUMBER_OF_COLUMNS){
				  if(board.getTile(x-i,y+i).getColor()==idp){
					  o++;
				  }
				  else{
					  break;
				  }
			  }
			  else{
				  break;
			  }
		  }
		  //pros ta pisw
		  if(o<2){
			  i=1;
			  while(true){
				  if(x+i<GomokuUtilities.NUMBER_OF_ROWS && y-i>=0){
					  if(board.getTile(x+i,y-i).getColor()==idp){
						  o++;
					  }
					  else{
						  fl=false;
						  break;
					  }
					  if(o==2 || i==2){
						  fl=false;
						  break;
					  }
					  i++;
				  }
				  else{
					  fl=false;
					  break;
				  }
			  }
		  }
		  
	  }while(o<2 && fl);
	  if(o==2){
		  return true;
	  }
	  else{
		  return false;
	  }
  }
  
  boolean createsDouble(int x,int y,Board board){
//h sunarthsh elegxei an gurw apo to plakidio uparxei estw ena pouli(akribws dipla)
	  if(y-1>=0){
		  //elegxos na mhn feugei eksw apo ta oria tou tamplo
		  if(board.getTile(x,y-1).getColor()==id){
			//elegxos gia to aristero plakidio
			  return true;
		  }
	  }
	  if(x-1>=0 && y-1>=0){
		  //elegxos na mhn feugei eksw apo ta oria tou tamplo
		  if(board.getTile(x,y-1).getColor()==id){
			//elegxos gia to panw aristera plakidio
			  return true;
		  }
	  }
	  if(y-1>=0){
		  //elegxos na mhn feugei eksw apo ta oria tou tamplo
		  if(board.getTile(x,y-1).getColor()==id ){
			//elegxos gia to panw plakidio
			  return true;
		  }
	  }
	  if( x-1>=0 && y>0){
		  //elegxos na mhn feugei eksw apo ta oria tou tamplo
		  if(board.getTile(x,y-1).getColor()==id){
			//elegxos gia to panw deksia plakidio
			  return true;
		  }
	  }
	  if(y>0){
		  //elegxos na mhn feugei eksw apo ta oria tou tamplo
		  if(board.getTile(x,y-1).getColor()==id){
			//elegxos gia to deksia plakidio
			  return true;
		  }
	  }
	  if(x+1<=GomokuUtilities.NUMBER_OF_ROWS && y>0){
		  //elegxos na mhn feugei eksw apo ta oria tou tamplo
		  if(board.getTile(x,y-1).getColor()==id){
			//elegxos gia to katw deksia plakidio
			  return true;
		  }
	  }
	  if(y>0){
		  //elegxos na mhn feugei eksw apo ta oria tou tamplo
		  if(board.getTile(x,y-1).getColor()==id){
			//elegxos gia to katw plakidio
			  return true;
		  }
	  }
	  if(x+1<=GomokuUtilities.NUMBER_OF_ROWS && y-1>=0){
		  //elegxos na mhn feugei eksw apo ta oria tou tamplo
		  if(board.getTile(x,y-1).getColor()==id){
			//elegxos gia to katw aristera plakidio
			  return true;
		  }
	  }
	  return false;
  }
  
  double centrality(int x,int y){
	  int medx,medy,medx1,medx2,medy1,medy2;
	  // medx->kentrikh seira //medy->kentrikh sthlh
	  
	  double distance;    //h apostash apo to kentro
	  double central;     //h kentrikothta   //antistrofa megalh ths apostashs
	  
	  //an oi seires einai perittos kai oi sthles perittos
	  if((GomokuUtilities.NUMBER_OF_ROWS % 2)== 1 && (GomokuUtilities.NUMBER_OF_COLUMNS % 2)==1){
		  medx=GomokuUtilities.NUMBER_OF_ROWS/2;
		  medy=GomokuUtilities.NUMBER_OF_COLUMNS/2;
		  
//distance=riza(ths orizontias apostashs apo kentrikh seira + ths katakorufhs apostashs apo kentrikh sthlh)
		  distance=Math.sqrt((medx-x)*(medx-x) + (medy-y)*(medy-y));
		  
//diairesh me thn riza(ths megisths orizontias + katakorufhs apostashs) gia na dwsei pedio timwn to [0,1]
		  distance=distance/Math.sqrt(medx*medx+medy*medy);
		  
	  }
	  //an oi seires perittos kai oi sthles artios
	  else if((GomokuUtilities.NUMBER_OF_ROWS % 2)== 1 && (GomokuUtilities.NUMBER_OF_COLUMNS % 2)==0){
		  medx=GomokuUtilities.NUMBER_OF_ROWS/2;
		  //exoume duo kentrikes sthles
		  medy1=GomokuUtilities.NUMBER_OF_COLUMNS/2-1;
		  medy2=GomokuUtilities.NUMBER_OF_COLUMNS/2;
		  //ek twn opoiwn ,analoga me thn timh tou y dialegoume thn mia gia na diaxeiristoume
		  if(y<=medy1){
			  medy=medy1;
		  }
		  else if(y>=medy2){
			  medy=medy2;
		  }
		  distance=Math.sqrt((medx-x)*(medx-x) + (medy-y)*(medy-y)); //paromoia opws panw
		  distance=distance/Math.sqrt(medx*medx+medy1*medy1);
	  }
	  //an oi seires artios kai oi sthles perittos
	  else if((GomokuUtilities.NUMBER_OF_ROWS % 2)== 0 && (GomokuUtilities.NUMBER_OF_COLUMNS % 2)==1){
		  //exoume duo kentrikes seires
		  medx1=GomokuUtilities.NUMBER_OF_ROWS/2-1;
		  medx2=GomokuUtilities.NUMBER_OF_ROWS/2;
		  medy=GomokuUtilities.NUMBER_OF_COLUMNS/2;
		//ek twn opoiwn ,analoga me thn timh tou x dialegoume thn mia gia na diaxeiristoume
		  if(x<=medx1){
			  medx=medx1;
			  
		  }
		  else if(x>=medx2){
			  medx=medx2;
		  }
		  distance=Math.sqrt((medx-x)*(medx-x) + (medy-y)*(medy-y));//paromoia opws panw
		  distance=distance/Math.sqrt(medx1*medx1+medy*medy);
	  }
	  //an oi seires artios kai oi sthles perittos
	  else{
		  //exoume duo kentrikes seires
		  medx1=GomokuUtilities.NUMBER_OF_ROWS/2-1;
		  medx2=GomokuUtilities.NUMBER_OF_ROWS/2;
		  //exoume duo kentrikes sthles
		  medy1=GomokuUtilities.NUMBER_OF_COLUMNS/2-1;
		  medy2=GomokuUtilities.NUMBER_OF_COLUMNS/2;
		//ek twn opoiwn ,analoga me thn timh tou x dialegoume thn mia gia na diaxeiristoume
		  if(x<=medx1){
			  medx=medx1;  
		  }
		  else if(x>=medx2){
			  medx=medx2;
		  }
		//ek twn opoiwn ,analoga me thn timh tou y dialegoume thn mia gia na diaxeiristoume
		  if(y<=medy1){
			  medy=medy1;
		  }
		  else if(y>=medy2){
			  medy=medy2;
		  }
		  distance=Math.sqrt((medx-x)*(medx-x) + (medy-y)*(medy-y));
		  distance=distance/Math.sqrt(medx1*medx1+medy1*medy1);//paromoia opws panw
	  }
	  central=distance-0.5; //diafora ths apostashs apo to meso
	  central=distance-2*central;
	  //an diafora>0  kentrikothta = apostash-2*|diafora|
	  //an diafora<0  kentrikothta = apostash+2*|diafora|
	  return central;
  }
  
  double evaluate (int x, int y, Board board){
	  //pws tha kanw thn double na epistrefei int????
	  double ev;
	  int e=0;
	  int enid; //tautothta antipalou (enemy id)
	  if(id==1){
		  enid=2;    //an tou paixth pou eksetazetai einai 1 tote tou antipalou einai 2 
	  }
	  else{
		  enid=1;    //kai antistrofa
	  }
	  if(createsQuintuple(x,y,board,id)){
		//an o paixths dhmiourgei pentada me thn topothethsh pouliou se auto to plakidio
		  e=100;
		  //h aksiologhsh einai h kaluterh dunath
		  return 100;
		  //kai epistrefetai amesws kathws opoiosdhpote allos elegxos einai perittos//to paixnidi teleiwse
	  }
	  //alliws
	  else if(createsQuintuple(x,y,board,enid)){
		  //an o antipalos dhmiourgei pentada me thn topothethsh pouliou se auto to plakidio
		  e=90;
		  //h aksiologhsh einai polu kalh!
	  }
	  //alliws
	  else if(createsQuartiple(x,y,board,id)){
		//an o paixths dhmiourgei tetrada me thn topothethsh pouliou se auto to plakidio
		  e=80;
		  //h aksiologhsh einai arketa kalh!
	  }
	  //alliws
	  else if(createsQuartiple(x,y,board,enid)){
		//an o antipalos dhmiourgei tetrada me thn topothethsh pouliou se auto to plakidio
		  e=70;
		  //h aksiologhsh einai sxedon arketa kalh
	  }
	  //alliws
	  else if(createsTriple(x,y,board,id)){
		//an o paixths dhmiourgei triada me thn topothethsh pouliou se auto to plakidio
		  e=60;
		  //h aksiologhsh einai kalh
	  }
	  //alliws
	  else if(createsTriple(x,y,board,enid)){
		//an o antipalos dhmiourgei triada me thn topothethsh pouliou se auto to plakidio
		  e=50;
		  //h aksiologhsh einai sxedon kalh
	  }
	  //alliws
	  else if(createsDouble(x,y,board)){
		  //an o paixths dhmiourgei duada me thn topothethsh pouliou se auto to plakidio
		  e=25;
		  //h aksiologhsh einai kapws kaluterh apo opoioudhpote allou apomonwmenou plakidiou
	  }
	  double cent=centrality(x,y); //epistrefei kentrikothta sto diasthma [0,1] oso megalutero toso kalutero
	  
	  double w=0;
	  if(x-4>=0 && y-4>=0 && x+4<=GomokuUtilities.NUMBER_OF_ROWS && y+4<=GomokuUtilities.NUMBER_OF_COLUMNS){
		  //an mporei na thewrithei mia perioxh me aktina 4 pou na mhn ksefeugei apo ta oria tou tamplo
		  int r=4;
		  w=GomokuUtilities.colorPercentage(board,x,y,r,id);
		  w*=10; 
		//to w epistrefei pososto kuriarxias sthn perioxh me aktina 4 sto diasthma [0,10] oso megalutero toso kalutero  
	  }
	  else if(x-3>=0 && y-3>=0 && x+3<=GomokuUtilities.NUMBER_OF_ROWS && y+3<=GomokuUtilities.NUMBER_OF_COLUMNS){
		//an mporei na thewrithei mia perioxh me aktina 3 pou na mhn ksefeugei apo ta oria tou tamplo
		  int r=3;
		  w=GomokuUtilities.colorPercentage(board,x,y,r,id);
		  w*=10; 
		//to w epistrefei pososto kuriarxias sthn perioxh me aktina 3 sto diasthma [0,10] oso megalutero toso kalutero  
	  }
	  else if(x-2>=0 && y-2>=0 && x+2<=GomokuUtilities.NUMBER_OF_ROWS && y+2<=GomokuUtilities.NUMBER_OF_COLUMNS){
		//an mporei na thewrithei mia perioxh me aktina 2 pou na mhn ksefeugei apo ta oria tou tamplo
		  int r=2;
		  w=GomokuUtilities.colorPercentage(board,x,y,r,id);
		  w*=10; 
		//to w epistrefei pososto kuriarxias sthn perioxh me aktina 2 sto diasthma [0,10] oso megalutero toso kalutero  
	  }
	  
	  ev=e+w+cent;
	  return ev;
  }

}


