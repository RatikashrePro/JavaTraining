const plans = [
  {
    name: "BasicLite",
    price: 249,
    data: 30,
    voice: 100,
    vrate: 0.75,
    smsRate: 0.2,
    smsfree: 0,
    ott: { netflix: false, prime: false, hotstar: false, spotify: false },
    desc: "1 GB/day, 100 mins included, ₹0.75/min overage, ₹0.20/SMS, OTT: None"
  },
  {
    name: "Saver30",
    price: 499,
    data: 45,
    voice: 300,
    vrate: 0.75,
    smsRate: 0.2,
    smsfree: 100,
    ott: { netflix: false, prime: false, hotstar: true, spotify: false },
    desc: "1.5 GB/day, 300 mins included, 100 SMS free, OTT: Hotstar"
  },
  {
    name: "UnlimitedTalk30",
    price: 650,
    data: 5,
    voice: Infinity,
    vrate: 0,
    smsRate: 0,
    smsfree: Infinity,
    ott: { netflix: false, prime: false, hotstar: false, spotify: true },
    desc: "5 GB total, Unlimited voice & SMS, OTT: Spotify"
  },
  {
    name: "DataMax20",
    price: 749,
    data: Infinity,
    voice: 100,
    vrate: 0.75,
    smsRate: 0,
    smsfree: Infinity,
    ott: { netflix: false, prime: false, hotstar: true, spotify: false },
    desc: "Unlimited data, 100 mins included, Unlimited SMS, OTT: Hotstar"
  },
  {
    name: "StudentStream56",
    price: 435,
    data: 60,
    voice: 300,
    vrate: 0.75,
    smsRate: 0.2,
    smsfree: 200,
    ott: { netflix: false, prime: false, hotstar: false, spotify: true },
    desc: "2 GB/day, 300 mins included, 200 SMS free, OTT: Spotify"
  },
  {
    name: "FamilyShare30",
    price: 500,
    data: 50,
    voice: 1000,
    vrate: 0.6,
    smsRate: 0.2,
    smsfree: 500,
    ott: { netflix: false, prime: true, hotstar: false, spotify: false },
    desc: "50 GB total, 1000 mins included, 500 SMS free, OTT: Amazon Prime"
  },
  {
    name: "DataMaxPlus30",
    price: 1499,
    data: Infinity,
    voice: 300,
    vrate: 0.75,
    smsRate: 0.2,
    smsfree: 200,
    ott: { netflix: false, prime: true, hotstar: true, spotify: false },
    desc: "Unlimited data, 300 mins included, 200 SMS free, OTT: Amazon Prime + Hotstar"
  },
  {
    name: "PremiumUltra30",
    price: 2999,
    data: Infinity,
    voice: Infinity,
    vrate: 0,
    smsRate: 0,
    smsfree: Infinity,
    ott: { netflix: true, prime: true, hotstar: true, spotify: true },
    desc: "Everything Unlimited + All OTTs"
  }
];

function listOTT(ottObj) {
  let svcs = [];
  if (ottObj.netflix) svcs.push("Netflix");
  if (ottObj.prime) svcs.push("Prime");
  if (ottObj.hotstar) svcs.push("Hotstar");
  if (ottObj.spotify) svcs.push("Spotify");
  return svcs.length > 0 ? svcs.join(", ") : "None";
}

function showAllPlanDetails() {
  let html = `<table border="1">
    <tr style="background:#224abe; color:#fff;">
      <th>Plan Name</th><th>Price (₹)</th><th>Data (GB)</th><th>Voice (mins)</th>
      <th>Voice Rate (₹/min)</th><th>SMS Rate (₹/SMS)</th><th>Free SMS</th><th>OTT</th><th>Description</th>
    </tr>`;
  plans.forEach(plan => {
    html += `<tr>
      <td>${plan.name}</td>
      <td>${plan.price}</td>
      <td>${plan.data === Infinity ? "Unlimited" : plan.data}</td>
      <td>${plan.voice === Infinity ? "Unlimited" : plan.voice}</td>
      <td>${plan.vrate}</td>
      <td>${plan.smsRate}</td>
      <td>${plan.smsfree === Infinity ? "Unlimited" : plan.smsfree}</td>
      <td>${listOTT(plan.ott)}</td>
      <td style="max-width:200px; text-align:left; overflow:hidden; white-space:nowrap; text-overflow:ellipsis;">${plan.desc}</td>
    </tr>`;
  });
  html += "</table>";
  document.getElementById("planDetails").innerHTML = html;
}

function calculatePlanCost(plan, voiceNeeded, smsNeeded, dataNeeded) {
  let extraVoice = plan.voice === Infinity ? 0 : Math.max(0, voiceNeeded - plan.voice) * plan.vrate;
  let extraSms = (plan.smsfree === Infinity || plan.smsRate === 0) ? 0 : Math.max(0, smsNeeded - plan.smsfree) * plan.smsRate;
  let extraData = plan.data === Infinity ? 0 : Math.max(0, dataNeeded - plan.data) * 70;
  return plan.price + extraVoice + extraSms + extraData;
}

function getBestPlanBasedOnUsage() {
  const voiceNeeded = parseInt(document.getElementById('voiceInput').value) || 0;
  const smsNeeded = parseInt(document.getElementById('smsInput').value) || 0;
  const dataNeeded = parseInt(document.getElementById('dataInput').value) || 0;

  let bestPlan = null;
  let minCost = Infinity;

  plans.forEach(plan => {
    let cost = calculatePlanCost(plan, voiceNeeded, smsNeeded, dataNeeded);
    if (cost < minCost) {
      minCost = cost;
      bestPlan = plan;
    }
  });

  displayResult(bestPlan, minCost);
}

function getBestPlanWithOTT() {
  const netflix = document.getElementById('netflix').checked;
  const prime = document.getElementById('prime').checked;
  const hotstar = document.getElementById('hotstar').checked;
  const spotify = document.getElementById('spotify').checked;

  const filtered = plans.filter(plan =>
    plan.ott.netflix === netflix &&
    plan.ott.prime === prime &&
    plan.ott.hotstar === hotstar &&
    plan.ott.spotify === spotify
  );

  if (filtered.length === 0) {
    document.getElementById('result').innerHTML = "No plans match your OTT preferences.";
    return;
  }

  filtered.sort((a, b) => a.price - b.price);

  displayResult(filtered[0], filtered[0].price);
}

function displayResult(plan, cost) {
  if (!plan) {
    document.getElementById('result').innerHTML = "No suitable plan found.";
    return;
  }
  document.getElementById('result').innerHTML = `
    <h3>Recommended Plan: ${plan.name}</h3>
    <p><b>Description:</b> ${plan.desc}</p>
    <p><b>OTT Services:</b> ${listOTT(plan.ott)}</p>
    <p><b>Estimated Cost:</b> ₹${cost.toFixed(2)}</p>
  `;
}
