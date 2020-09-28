# 💰 Lucky Rewards
Esse plugin é open-source e tem todos os direitos reservados.
## 📚 Desenvolvedores

Como criar uma reward com os seguintes parametros:
```java

//Criará uma reward com os parametros definidos
Reward reward = new Reward(rewardName, id, delay);

//Pegando os detalhes da reward que foi criada a cima.
int id = reward.getId();
int delay = reward.getDelay();
String name = reward.getName();
List<ItemStack> items = reward.getItems();
```

#### Manuseamento
```java
//Como pegar uma reward pelo nome/id
Reward reward = RewardManager.getRewardByName("name");
Reward rewardGetByID = RewardManager.getRewardByID(id);
```


### Download
Download all source code.\
![Download](https://i.imgur.com/0sWqvh5.png)

### Releases
Download a stable or developing release (.jar file).\
![Releases](https://i.imgur.com/5LfJVVC.png)

### Como usar
1. Compile usando Gradle ou converta para maven.
2. Vá até: `SeuDisco:\SuaPasta\LuckyRewards\build\libs`
3. Pegue a .jar e coloque em `/plugins` do seu servidor.